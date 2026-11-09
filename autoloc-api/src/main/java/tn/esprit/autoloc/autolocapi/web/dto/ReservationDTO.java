package tn.esprit.autoloc.autolocapi.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationDTO {

    private Long idReservation;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    @NotNull(message = "Le statut est obligatoire")
    private StatutReservation statut;

    @NotNull(message = "Le véhicule est obligatoire")
    private Long vehiculeId;

    private String vehiculeImmatriculation;

    @NotNull(message = "Le client est obligatoire")
    private Long clientId;

    private String clientNom;
    private String clientPrenom;
}
