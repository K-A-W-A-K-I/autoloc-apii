package tn.esprit.autoloc.autolocapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@AllArgsConstructor
@CrossOrigin("*")
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    /**
     * Récupérer tous les véhicules
     * GET http://localhost:8080/api/vehicules
     */
    @GetMapping
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeService.retrieveAllVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    /**
     * Récupérer un véhicule par son ID
     * GET http://localhost:8080/api/vehicules/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable("id") Long idVehicule) {
        Vehicule vehicule = vehiculeService.retrieveVehicule(idVehicule);
        return new ResponseEntity<>(vehicule, HttpStatus.OK);
    }

    /**
     * Ajouter un nouveau véhicule
     * POST http://localhost:8080/api/vehicules
     */
    @PostMapping
    public ResponseEntity<Vehicule> addVehicule(@RequestBody Vehicule vehicule) {
        Vehicule newVehicule = vehiculeService.addVehicule(vehicule);
        return new ResponseEntity<>(newVehicule, HttpStatus.CREATED);
    }

    /**
     * Mettre à jour un véhicule
     * PUT http://localhost:8080/api/vehicules/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable("id") Long idVehicule, @RequestBody Vehicule vehicule) {
        vehicule.setIdVehicule(idVehicule);
        Vehicule updatedVehicule = vehiculeService.updateVehicule(vehicule);
        return new ResponseEntity<>(updatedVehicule, HttpStatus.OK);
    }

    /**
     * Supprimer un véhicule
     * DELETE http://localhost:8080/api/vehicules/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable("id") Long idVehicule) {
        vehiculeService.removeVehicule(idVehicule);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Rechercher un véhicule par immatriculation
     * GET http://localhost:8080/api/vehicules/immatriculation/{immat}
     */
    @GetMapping("/immatriculation/{immat}")
    public ResponseEntity<Vehicule> getVehiculeByImmatriculation(@PathVariable("immat") String immatriculation) {
        Vehicule vehicule = vehiculeService.findByImmatriculation(immatriculation);
        return new ResponseEntity<>(vehicule, HttpStatus.OK);
    }

    /**
     * Rechercher des véhicules par statut
     * GET http://localhost:8080/api/vehicules/statut/{statut}
     */
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Vehicule>> getVehiculesByStatut(@PathVariable("statut") StatutVehicule statut) {
        List<Vehicule> vehicules = vehiculeService.findByStatut(statut);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    /**
     * Rechercher des véhicules par catégorie
     * GET http://localhost:8080/api/vehicules/categorie/{categorie}
     */
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Vehicule>> getVehiculesByCategorie(@PathVariable("categorie") CategorieVehicule categorie) {
        List<Vehicule> vehicules = vehiculeService.findByCategorie(categorie);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }
}
