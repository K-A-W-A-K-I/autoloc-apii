package tn.esprit.autoloc.autolocapi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;

    @Override
    public List<Vehicule> retrieveAllVehicules() {
        log.info("Récupération de tous les véhicules");
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {
        log.info("Récupération du véhicule avec l'ID: {}", idVehicule);
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec l'ID: " + idVehicule));
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        log.info("Ajout d'un nouveau véhicule: {} {}", vehicule.getMarque(), vehicule.getModele());
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        log.info("Mise à jour du véhicule avec l'ID: {}", vehicule.getIdVehicule());
        if (!vehiculeRepository.existsById(vehicule.getIdVehicule())) {
            throw new RuntimeException("Véhicule non trouvé avec l'ID: " + vehicule.getIdVehicule());
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        log.info("Suppression du véhicule avec l'ID: {}", idVehicule);
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new RuntimeException("Véhicule non trouvé avec l'ID: " + idVehicule);
        }
        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    public List<Vehicule> findByStatut(StatutVehicule statut) {
        log.info("Recherche des véhicules avec le statut: {}", statut);
        return vehiculeRepository.findByStatut(statut);
    }

    @Override
    public List<Vehicule> findByCategorie(CategorieVehicule categorie) {
        log.info("Recherche des véhicules de la catégorie: {}", categorie);
        return vehiculeRepository.findByCategorie(categorie);
    }

    @Override
    public Vehicule findByImmatriculation(String immatriculation) {
        log.info("Recherche du véhicule avec l'immatriculation: {}", immatriculation);
        return vehiculeRepository.findByImmatriculation(immatriculation)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec l'immatriculation: " + immatriculation));
    }
}
