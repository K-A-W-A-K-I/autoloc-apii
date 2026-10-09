package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    List<Paiement> retrieveAllPaiements();
    Paiement retrievePaiement(Long idPaiement);
    Paiement addPaiement(Paiement paiement);
    Paiement updatePaiement(Paiement paiement);
    void removePaiement(Long idPaiement);
}
