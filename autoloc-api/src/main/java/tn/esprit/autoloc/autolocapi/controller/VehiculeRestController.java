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
        List<Vehicule> vehicules = vehiculeService.findAll();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    /**
     * Récupérer un véhicule par son ID
     * GET http://localhost:8080/api/vehicules/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable("id") Long idVehicule) {
        Vehicule vehicule = vehiculeService.findById(idVehicule);
        return new ResponseEntity<>(vehicule, HttpStatus.OK);
    }

    /**
     * Ajouter un nouveau véhicule
     * POST http://localhost:8080/api/vehicules
     */
    @PostMapping
    public ResponseEntity<Vehicule> addVehicule(@RequestBody Vehicule vehicule) {
        Vehicule newVehicule = vehiculeService.create(vehicule);
        return new ResponseEntity<>(newVehicule, HttpStatus.CREATED);
    }

    /**
     * Mettre à jour un véhicule
     * PUT http://localhost:8080/api/vehicules/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable("id") Long idVehicule, @RequestBody Vehicule vehicule) {
        Vehicule updatedVehicule = vehiculeService.update(idVehicule, vehicule);
        return new ResponseEntity<>(updatedVehicule, HttpStatus.OK);
    }

    /**
     * Supprimer un véhicule
     * DELETE http://localhost:8080/api/vehicules/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable("id") Long idVehicule) {
        vehiculeService.deleteById(idVehicule);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
