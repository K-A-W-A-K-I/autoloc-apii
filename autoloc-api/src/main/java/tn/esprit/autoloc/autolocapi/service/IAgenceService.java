package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Agence;

import java.util.List;

public interface IAgenceService {
    List<Agence> retrieveAllAgences();
    Agence retrieveAgence(Long idAgence);
    Agence addAgence(Agence agence);
    Agence updateAgence(Agence agence);
    void removeAgence(Long idAgence);
    List<Agence> findByVille(String ville);
}
