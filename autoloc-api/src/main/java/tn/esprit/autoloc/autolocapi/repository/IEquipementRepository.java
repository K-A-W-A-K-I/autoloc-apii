package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Equipement;

import java.util.Optional;

@Repository
public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
    Optional<Equipement> findByLibelle(String libelle);
}
