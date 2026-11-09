package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EquipementDTO {
  private Long idEquipement;
  private String libelle;
}
