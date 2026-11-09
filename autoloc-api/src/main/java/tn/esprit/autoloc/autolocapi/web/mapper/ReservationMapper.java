package tn.esprit.autoloc.autolocapi.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;
import tn.esprit.autoloc.autolocapi.web.dto.ReservationDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

  private final IVehiculeRepository vehiculeRepository;
  private final IClientRepository clientRepository;

  public ReservationDTO toDTO(Reservation reservation) {
    if (reservation == null) {
      return null;
    }
    return ReservationDTO.builder()
        .idReservation(reservation.getIdReservation())
        .dateDebut(reservation.getDateDebut())
        .dateFin(reservation.getDateFin())
        .statut(reservation.getStatut())
        .vehiculeId(reservation.getVehicule() != null ? reservation.getVehicule().getIdVehicule() : null)
        .vehiculeImmatriculation(reservation.getVehicule() != null ? reservation.getVehicule().getImmatriculation() : null)
        .clientId(reservation.getClient() != null ? reservation.getClient().getIdClient() : null)
        .clientNom(reservation.getClient() != null ? reservation.getClient().getNom() : null)
        .clientPrenom(reservation.getClient() != null ? reservation.getClient().getPrenom() : null)
        .build();
  }

  public Reservation toEntity(ReservationDTO dto) {
    if (dto == null) {
      return null;
    }
    Reservation reservation = new Reservation();
    reservation.setIdReservation(dto.getIdReservation());
    reservation.setDateDebut(dto.getDateDebut());
    reservation.setDateFin(dto.getDateFin());
    reservation.setStatut(dto.getStatut());
    
    if (dto.getVehiculeId() != null) {
      Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId()).orElse(null);
      reservation.setVehicule(vehicule);
    }
    
    if (dto.getClientId() != null) {
      Client client = clientRepository.findById(dto.getClientId()).orElse(null);
      reservation.setClient(client);
    }
    
    return reservation;
  }

  public List<ReservationDTO> toDTOList(List<Reservation> reservations) {
    return reservations.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
