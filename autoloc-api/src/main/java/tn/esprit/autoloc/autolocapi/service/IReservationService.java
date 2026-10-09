package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(Long idReservation);
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void removeReservation(Long idReservation);
    List<Reservation> findByStatut(StatutReservation statut);
}
