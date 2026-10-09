package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    List<Equipement> retrieveAllEquipements();
    Equipement retrieveEquipement(Long idEquipement);
    Equipement addEquipement(Equipement equipement);
    Equipement updateEquipement(Equipement equipement);
    void removeEquipement(Long idEquipement);
}
