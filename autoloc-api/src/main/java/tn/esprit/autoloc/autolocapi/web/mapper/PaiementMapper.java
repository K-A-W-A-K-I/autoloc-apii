package tn.esprit.autoloc.autolocapi.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.web.dto.PaiementDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaiementMapper {

  private final IContratRepository contratRepository;

  public PaiementDTO toDTO(Paiement paiement) {
    if (paiement == null) {
      return null;
    }
    return PaiementDTO.builder()
        .idPaiement(paiement.getIdPaiement())
        .montant(paiement.getMontant())
        .datePaiement(paiement.getDatePaiement())
        .modePaiement(paiement.getModePaiement())
        .contratId(paiement.getContrat() != null ? paiement.getContrat().getIdContrat() : null)
        .build();
  }

  public Paiement toEntity(PaiementDTO dto) {
    if (dto == null) {
      return null;
    }
    Paiement paiement = new Paiement();
    paiement.setIdPaiement(dto.getIdPaiement());
    paiement.setMontant(dto.getMontant());
    paiement.setDatePaiement(dto.getDatePaiement());
    paiement.setModePaiement(dto.getModePaiement());
    
    if (dto.getContratId() != null) {
      Contrat contrat = contratRepository.findById(dto.getContratId()).orElse(null);
      paiement.setContrat(contrat);
    }
    
    return paiement;
  }

  public List<PaiementDTO> toDTOList(List<Paiement> paiements) {
    return paiements.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
