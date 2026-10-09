package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.domain.ModePaiement;
import tn.esprit.autoloc.autolocapi.domain.Paiement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByContrat(Contrat contrat);
    List<Paiement> findByModePaiement(ModePaiement modePaiement);
    
    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.datePaiement BETWEEN :dateDebut AND :dateFin")
    BigDecimal calculateTotalRevenueBetweenDates(LocalDate dateDebut, LocalDate dateFin);
    
    List<Paiement> findByDatePaiementBetween(LocalDate dateDebut, LocalDate dateFin);
}
