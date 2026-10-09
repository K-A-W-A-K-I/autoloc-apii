package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Client;

import java.util.List;

public interface IClientService {
    List<Client> retrieveAllClients();
    Client retrieveClient(Long idClient);
    Client addClient(Client client);
    Client updateClient(Client client);
    void removeClient(Long idClient);
    Client findByEmail(String email);
    Client findByNumPermis(String numPermis);
}
