package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByVehicule(Vehicule vehicule);
    
    @Query("SELECT m FROM Maintenance m WHERE m.vehicule = :vehicule ORDER BY m.dateDebut DESC")
    List<Maintenance> findByVehiculeOrderByDateDesc(Vehicule vehicule);
    
    List<Maintenance> findByDateDebutBetween(LocalDate dateDebut, LocalDate dateFin);
}
