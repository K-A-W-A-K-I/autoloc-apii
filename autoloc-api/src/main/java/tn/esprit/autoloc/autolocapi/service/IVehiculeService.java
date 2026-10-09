package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    List<Vehicule> retrieveAllVehicules();
    Vehicule retrieveVehicule(Long idVehicule);
    Vehicule addVehicule(Vehicule vehicule);
    Vehicule updateVehicule(Vehicule vehicule);
    void removeVehicule(Long idVehicule);
    List<Vehicule> findByStatut(StatutVehicule statut);
    List<Vehicule> findByCategorie(CategorieVehicule categorie);
    Vehicule findByImmatriculation(String immatriculation);
}
