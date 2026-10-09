package tn.esprit.autoloc.autolocapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
@CrossOrigin("*")
public class ClientRestController {

    private final IClientService clientService;

    /**
     * Récupérer tous les clients
     * GET http://localhost:8080/api/clients
     */
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.retrieveAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Récupérer un client par son ID
     * GET http://localhost:8080/api/clients/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long idClient) {
        Client client = clientService.retrieveClient(idClient);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Ajouter un nouveau client
     * POST http://localhost:8080/api/clients
     */
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = clientService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    /**
     * Mettre à jour un client
     * PUT http://localhost:8080/api/clients/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long idClient, @RequestBody Client client) {
        client.setIdClient(idClient);
        Client updatedClient = clientService.updateClient(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    /**
     * Supprimer un client
     * DELETE http://localhost:8080/api/clients/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long idClient) {
        clientService.removeClient(idClient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Rechercher un client par email
     * GET http://localhost:8080/api/clients/email/{email}
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable("email") String email) {
        Client client = clientService.findByEmail(email);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Rechercher un client par numéro de permis
     * GET http://localhost:8080/api/clients/permis/{numPermis}
     */
    @GetMapping("/permis/{numPermis}")
    public ResponseEntity<Client> getClientByNumPermis(@PathVariable("numPermis") String numPermis) {
        Client client = clientService.findByNumPermis(numPermis);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
