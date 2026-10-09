# Notes - Atelier 3 : Couche Repository Spring Data JPA

## 1. Choix des interfaces Repository

| Interface | Étend | Justification |
|-----------|-------|---------------|
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible pour forcer l'écriture immédiate. Gestion de la cascade vers Paiement. |
| IPaiementRepository | JpaRepository<Paiement, Long> | CRUD complet pour lire les paiements, bien que leur création/suppression passe normalement par le Contrat parent (composition). |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet, List retournée, méthodes personnalisées findByEmail et findByNumPermis pour la recherche client. |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet, tri et pagination disponibles pour lister les véhicules par statut, catégorie ou agence. |
| IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet, méthodes de recherche par client, véhicule et statut. Gestion des conflits de réservation. |
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet, recherche par ville, gestion de la relation avec employés et véhicules. |
| IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet, recherche par agence et rôle pour la gestion du personnel. |
| IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet, recherche par libellé, relation ManyToMany avec Vehicule. |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet, recherche par véhicule et dates pour l'historique de maintenance. |

### Pourquoi JpaRepository ?

- **CRUD complet** : save(), findById(), findAll(), delete(), etc.
- **Collections en List** : Plus pratique que Iterable (size(), get(), stream())
- **Tri et pagination** : findAll(Sort), findAll(Pageable)
- **Méthodes JPA spécifiques** : flush(), saveAndFlush(), getReferenceById()
- **Performance** : deleteAllInBatch() pour suppressions en lot (attention à la cascade!)

## 2. Anomalies détectées et corrigées avec SonarQube for IDE

### Anomalie 1 : Imports inutilisés
**Règle** : `java:S1128` - Unused imports should be removed  
**Détection** : Imports non utilisés dans les classes de service et entités  
**Correction** : Suppression des imports inutilisés dans toutes les classes

### Anomalie 2 : Utilisation de @Data sur les entités JPA
**Règle** : `java:S6813` - Avoid using Lombok's @Data on JPA entities  
**Détection** : @Data génère equals/hashCode/toString qui peuvent causer des problèmes avec les proxies Hibernate  
**Correction** : Utilisation de @Getter @Setter @NoArgsConstructor @AllArgsConstructor au lieu de @Data

### Anomalie 3 : Méthodes avec trop de paramètres
**Règle** : `java:S107` - Methods should not have too many parameters  
**Détection** : Constructeurs générés par @AllArgsConstructor avec beaucoup de paramètres  
**Correction** : Acceptable pour les entités JPA car utilisé par Hibernate, mais utilisation de builders recommandée pour la création manuelle

### Anomalie 4 : Logs SQL en production
**Règle** : Security - Logging SQL statements  
**Détection** : spring.jpa.show-sql=true dans application.properties  
**Correction** : À désactiver en production, acceptable en développement pour le débogage

### Anomalie 5 : Mot de passe en dur dans application.properties
**Règle** : `java:S2068` - Credentials should not be hard-coded  
**Détection** : spring.datasource.password=110804 en clair  
**Correction** : Utiliser des variables d'environnement ou un gestionnaire de secrets en production

## 3. Conventions de nommage respectées

- ✅ Préfixe `I` pour toutes les interfaces repository (IContratRepository, etc.)
- ✅ Package `repository` distinct du package `domain`
- ✅ Convention camelCase pour les méthodes de recherche (findByEmail, findByStatut)
- ✅ Utilisation de Optional<T> pour les résultats uniques potentiellement absents

## 4. Organisation en couches

```
tn.esprit.autoloc.autolocapi/
├── domain/              ← Entités JPA (9 classes)
├── repository/          ← Interfaces Spring Data (9 interfaces avec préfixe I)
├── service/             ← Interfaces et implémentations métier
└── controller/          ← REST Controllers
```

## 5. Points d'attention

### Cascade et orphanRemoval
- **Contrat → Paiement** : `cascade = ALL`, `orphanRemoval = true`
  - Supprimer un contrat supprime automatiquement ses paiements
  - Retirer un paiement de la collection le supprime de la base

### Méthodes save()
- **ID null** → INSERT (nouveau contrat)
- **ID renseigné** → SELECT puis UPDATE (merge)
- Toujours utiliser l'objet retourné par save()

### Suppressions
- `deleteById(id)` : Passe par le contexte de persistance, cascade appliquée
- `deleteAllInBatch()` : DELETE direct, contourne la cascade ⚠️

## 6. Logs Spring Data observés

Au démarrage de l'application :
```
Found 9 JPA repository interfaces
Creating shared instance of singleton bean 'IContratRepository'
Creating shared instance of singleton bean 'IPaiementRepository'
...
```

## 7. Tests manuels effectués

- ✅ Démarrage de l'application sans erreur
- ✅ Vérification des 9 repositories détectés dans les logs
- ✅ Schéma de base de données généré correctement
- ✅ Relations et clés étrangères créées

## 8. Améliorations futures

- Ajouter des méthodes de recherche personnalisées avec @Query
- Implémenter la pagination pour les listes volumineuses
- Ajouter des projections pour optimiser les requêtes
- Créer des spécifications pour les recherches complexes
- Ajouter des tests unitaires avec @DataJpaTest
