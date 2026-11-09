package tn.esprit.autoloc.autolocapi.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;
import tn.esprit.autoloc.autolocapi.web.dto.ContratDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContratMapper {

  private final IReservationRepository reservationRepository;
  private final PaiementMapper paiementMapper;

  public ContratDTO toDTO(Contrat contrat) {
    if (contrat == null) {
      return null;
    }
    return ContratDTO.builder()
        .idContrat(contrat.getIdContrat())
        .dateSignature(contrat.getDateSignature())
        .montantTotal(contrat.getMontantTotal())
        .valide(contrat.isValide())
        .reservationId(contrat.getReservation() != null ? contrat.getReservation().getIdReservation() : null)
        .paiements(contrat.getPaiements() != null ? paiementMapper.toDTOList(contrat.getPaiements()) : null)
        .build();
  }

  public Contrat toEntity(ContratDTO dto) {
    if (dto == null) {
      return null;
    }
    Contrat contrat = new Contrat();
    contrat.setIdContrat(dto.getIdContrat());
    contrat.setDateSignature(dto.getDateSignature());
    contrat.setMontantTotal(dto.getMontantTotal());
    contrat.setValide(dto.isValide());
    
    if (dto.getReservationId() != null) {
      Reservation reservation = reservationRepository.findById(dto.getReservationId()).orElse(null);
      contrat.setReservation(reservation);
    }
    
    if (dto.getPaiements() != null) {
      contrat.setPaiements(dto.getPaiements().stream()
          .map(paiementMapper::toEntity)
          .collect(Collectors.toList()));
    }
    
    return contrat;
  }

  public List<ContratDTO> toDTOList(List<Contrat> contrats) {
    return contrats.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
