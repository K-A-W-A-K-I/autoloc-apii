package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.ModePaiement;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaiementDTO {
  private Long idPaiement;
  private BigDecimal montant;
  private LocalDate datePaiement;
  private ModePaiement modePaiement;
  private Long contratId;
}
