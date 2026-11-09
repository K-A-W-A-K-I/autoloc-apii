package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClientDTO {
  private Long idClient;
  private String nom;
  private String prenom;
  private String email;
  private String telephone;
  private String numPermis;
  private LocalDate dateInscription;
}
