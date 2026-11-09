package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AgenceDTO {
  private Long idAgence;
  private String nom;
  private String ville;
  private String adresse;
  private String telephone;
}
