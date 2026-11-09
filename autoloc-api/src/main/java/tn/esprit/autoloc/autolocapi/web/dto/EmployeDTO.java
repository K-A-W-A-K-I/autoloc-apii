package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.RoleEmploye;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeDTO {
  private Long idEmploye;
  private String nom;
  private String prenom;
  private RoleEmploye role;
  private Long agenceId;
  private String agenceNom;
}
