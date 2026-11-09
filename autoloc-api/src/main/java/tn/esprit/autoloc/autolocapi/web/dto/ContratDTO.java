package tn.esprit.autoloc.autolocapi.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContratDTO {

    private Long idContrat;

    @NotNull(message = "La date de signature est obligatoire")
    private LocalDate dateSignature;

    @NotNull(message = "Le montant total est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant total doit être supérieur à 0")
    @Digits(integer = 8, fraction = 2, message = "Le montant doit avoir au maximum 8 chiffres entiers et 2 décimales")
    private BigDecimal montantTotal;

    private boolean valide;

    @NotNull(message = "La réservation est obligatoire")
    private Long reservationId;

    @Valid
    private List<PaiementDTO> paiements;
}
