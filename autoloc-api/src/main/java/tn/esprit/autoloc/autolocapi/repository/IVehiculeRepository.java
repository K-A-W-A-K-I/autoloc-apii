package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
    Optional<Vehicule> findByImmatriculation(String immatriculation);
    List<Vehicule> findByStatut(StatutVehicule statut);
    List<Vehicule> findByCategorie(CategorieVehicule categorie);
    List<Vehicule> findByAgence(Agence agence);
    List<Vehicule> findByMarque(String marque);
    
    @Query("SELECT v FROM Vehicule v WHERE v.statut = 'DISPONIBLE' AND v.categorie = :categorie")
    List<Vehicule> findAvailableVehiculesByCategorie(CategorieVehicule categorie);
    
    List<Vehicule> findByTarifJournalierLessThanEqual(BigDecimal tarif);
}
