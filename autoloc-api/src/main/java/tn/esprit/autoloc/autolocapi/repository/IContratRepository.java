package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.domain.Reservation;

import java.util.Optional;

@Repository
public interface IContratRepository extends JpaRepository<Contrat, Long> {
    Optional<Contrat> findByReservation(Reservation reservation);
}
