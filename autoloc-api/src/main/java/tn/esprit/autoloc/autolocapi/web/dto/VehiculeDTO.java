package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VehiculeDTO {
  private Long idVehicule;
  private String immatriculation;
  private String marque;
  private String modele;
  private CategorieVehicule categorie;
  private BigDecimal tarifJournalier;
  private StatutVehicule statut;
  private Long agenceId;
  private String agenceNom;
  private List<Long> equipementIds;
}
