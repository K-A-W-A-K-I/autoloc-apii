package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.domain.RoleEmploye;

import java.util.List;

@Repository
public interface IEmployeRepository extends JpaRepository<Employe, Long> {
    List<Employe> findByAgence(Agence agence);
    List<Employe> findByRole(RoleEmploye role);
    List<Employe> findByNomAndPrenom(String nom, String prenom);
}
