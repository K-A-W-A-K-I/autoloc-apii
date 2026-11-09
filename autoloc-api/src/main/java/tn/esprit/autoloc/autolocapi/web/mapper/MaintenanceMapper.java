package tn.esprit.autoloc.autolocapi.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;
import tn.esprit.autoloc.autolocapi.web.dto.MaintenanceDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MaintenanceMapper {

  private final IVehiculeRepository vehiculeRepository;

  public MaintenanceDTO toDTO(Maintenance maintenance) {
    if (maintenance == null) {
      return null;
    }
    return MaintenanceDTO.builder()
        .idMaintenance(maintenance.getIdMaintenance())
        .dateDebut(maintenance.getDateDebut())
        .dateFin(maintenance.getDateFin())
        .description(maintenance.getDescription())
        .vehiculeId(maintenance.getVehicule() != null ? maintenance.getVehicule().getIdVehicule() : null)
        .vehiculeImmatriculation(maintenance.getVehicule() != null ? maintenance.getVehicule().getImmatriculation() : null)
        .build();
  }

  public Maintenance toEntity(MaintenanceDTO dto) {
    if (dto == null) {
      return null;
    }
    Maintenance maintenance = new Maintenance();
    maintenance.setIdMaintenance(dto.getIdMaintenance());
    maintenance.setDateDebut(dto.getDateDebut());
    maintenance.setDateFin(dto.getDateFin());
    maintenance.setDescription(dto.getDescription());
    
    if (dto.getVehiculeId() != null) {
      Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId()).orElse(null);
      maintenance.setVehicule(vehicule);
    }
    
    return maintenance;
  }

  public List<MaintenanceDTO> toDTOList(List<Maintenance> maintenances) {
    return maintenances.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
