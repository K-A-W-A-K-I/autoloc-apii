package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClient(Client client);
    List<Reservation> findByVehicule(Vehicule vehicule);
    List<Reservation> findByStatut(StatutReservation statut);
    
    @Query("SELECT r FROM Reservation r WHERE r.client = :client AND r.statut = :statut")
    List<Reservation> findByClientAndStatut(Client client, StatutReservation statut);
    
    @Query("SELECT r FROM Reservation r WHERE r.vehicule = :vehicule AND " +
           "(r.dateDebut BETWEEN :dateDebut AND :dateFin OR " +
           "r.dateFin BETWEEN :dateDebut AND :dateFin)")
    List<Reservation> findConflictingReservations(Vehicule vehicule, LocalDate dateDebut, LocalDate dateFin);
}
