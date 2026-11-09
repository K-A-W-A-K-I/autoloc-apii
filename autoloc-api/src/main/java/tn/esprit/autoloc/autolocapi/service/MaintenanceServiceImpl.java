package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;
import tn.esprit.autoloc.autolocapi.web.dto.MaintenanceDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.MaintenanceMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceDTO create(MaintenanceDTO maintenanceDTO) {
        if (maintenanceDTO.getIdMaintenance() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle maintenance ne doit pas avoir d'identifiant");
        }
        if (maintenanceDTO.getDateFin() != null && maintenanceDTO.getDateDebut() != null
                && maintenanceDTO.getDateFin().isBefore(maintenanceDTO.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }
        Maintenance maintenance = maintenanceMapper.toEntity(maintenanceDTO);
        Maintenance saved = maintenanceRepository.save(maintenance);
        return maintenanceMapper.toDTO(saved);
    }

    @Override
    public MaintenanceDTO findById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
        return maintenanceMapper.toDTO(maintenance);
    }

    @Override
    public List<MaintenanceDTO> findAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        return maintenanceMapper.toDTOList(maintenances);
    }

    @Override
    public MaintenanceDTO update(Long id, MaintenanceDTO maintenanceDTO) {
        Maintenance existant = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
        if (maintenanceDTO.getDateFin() != null && maintenanceDTO.getDateDebut() != null
                && maintenanceDTO.getDateFin().isBefore(maintenanceDTO.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }
        existant.setDateDebut(maintenanceDTO.getDateDebut());
        existant.setDateFin(maintenanceDTO.getDateFin());
        existant.setDescription(maintenanceDTO.getDescription());
        Maintenance fromDTO = maintenanceMapper.toEntity(maintenanceDTO);
        existant.setVehicule(fromDTO.getVehicule());
        Maintenance updated = maintenanceRepository.save(existant);
        return maintenanceMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        maintenanceRepository.deleteById(id);
    }
}
