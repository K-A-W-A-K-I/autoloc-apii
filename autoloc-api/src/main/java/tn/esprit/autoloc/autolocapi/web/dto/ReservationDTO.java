package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationDTO {
  private Long idReservation;
  private LocalDate dateDebut;
  private LocalDate dateFin;
  private StatutReservation statut;
  private Long vehiculeId;
  private String vehiculeImmatriculation;
  private Long clientId;
  private String clientNom;
  private String clientPrenom;
}
