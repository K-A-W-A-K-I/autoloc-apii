package tn.esprit.autoloc.autolocapi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> retrieveAllClients() {
        log.info("Récupération de tous les clients");
        return clientRepository.findAll();
    }

    @Override
    public Client retrieveClient(Long idClient) {
        log.info("Récupération du client avec l'ID: {}", idClient);
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID: " + idClient));
    }

    @Override
    public Client addClient(Client client) {
        log.info("Ajout d'un nouveau client: {} {}", client.getNom(), client.getPrenom());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        log.info("Mise à jour du client avec l'ID: {}", client.getIdClient());
        if (!clientRepository.existsById(client.getIdClient())) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + client.getIdClient());
        }
        return clientRepository.save(client);
    }

    @Override
    public void removeClient(Long idClient) {
        log.info("Suppression du client avec l'ID: {}", idClient);
        if (!clientRepository.existsById(idClient)) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + idClient);
        }
        clientRepository.deleteById(idClient);
    }

    @Override
    public Client findByEmail(String email) {
        log.info("Recherche du client avec l'email: {}", email);
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'email: " + email));
    }

    @Override
    public Client findByNumPermis(String numPermis) {
        log.info("Recherche du client avec le numéro de permis: {}", numPermis);
        return clientRepository.findByNumPermis(numPermis)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec le numéro de permis: " + numPermis));
    }
}
