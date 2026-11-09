package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MaintenanceDTO {
  private Long idMaintenance;
  private LocalDate dateDebut;
  private LocalDate dateFin;
  private String description;
  private Long vehiculeId;
  private String vehiculeImmatriculation;
}
