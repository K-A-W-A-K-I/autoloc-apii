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
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Récupérer un client par son ID
     * GET http://localhost:8080/api/clients/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long idClient) {
        Client client = clientService.findById(idClient);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Ajouter un nouveau client
     * POST http://localhost:8080/api/clients
     */
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = clientService.create(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    /**
     * Mettre à jour un client
     * PUT http://localhost:8080/api/clients/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long idClient, @RequestBody Client client) {
        Client updatedClient = clientService.update(idClient, client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    /**
     * Supprimer un client
     * DELETE http://localhost:8080/api/clients/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long idClient) {
        clientService.deleteById(idClient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
