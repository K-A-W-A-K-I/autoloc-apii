package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.web.dto.MaintenanceDTO;

import java.util.List;

public interface IMaintenanceService {
    MaintenanceDTO create(MaintenanceDTO maintenanceDTO);
    MaintenanceDTO findById(Long id);
    List<MaintenanceDTO> findAll();
    MaintenanceDTO update(Long id, MaintenanceDTO maintenanceDTO);
    void deleteById(Long id);
}
