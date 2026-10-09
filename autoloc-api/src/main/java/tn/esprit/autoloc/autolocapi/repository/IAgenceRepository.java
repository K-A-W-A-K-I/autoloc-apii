package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Agence;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAgenceRepository extends JpaRepository<Agence, Long> {
    Optional<Agence> findByNom(String nom);
    List<Agence> findByVille(String ville);
}
