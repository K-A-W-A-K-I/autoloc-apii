package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.web.dto.VehiculeDTO;

import java.util.List;

public interface IVehiculeService {
    VehiculeDTO create(VehiculeDTO vehiculeDTO);
    VehiculeDTO findById(Long id);
    List<VehiculeDTO> findAll();
    VehiculeDTO update(Long id, VehiculeDTO vehiculeDTO);
    void deleteById(Long id);
}
