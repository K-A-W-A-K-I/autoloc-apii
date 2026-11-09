package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContratDTO {
  private Long idContrat;
  private LocalDate dateSignature;
  private BigDecimal montantTotal;
  private boolean valide;
  private Long reservationId;
  private List<PaiementDTO> paiements;
}
