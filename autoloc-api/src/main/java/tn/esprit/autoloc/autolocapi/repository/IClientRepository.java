package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Client;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByNumPermis(String numPermis);
    boolean existsByEmail(String email);
    boolean existsByNumPermis(String numPermis);
}
