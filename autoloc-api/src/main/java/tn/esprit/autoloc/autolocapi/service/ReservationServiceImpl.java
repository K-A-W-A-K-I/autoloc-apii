package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;
import tn.esprit.autoloc.autolocapi.web.dto.ReservationDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.ReservationMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationDTO create(ReservationDTO reservationDTO) {
        if (reservationDTO.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle réservation ne doit pas avoir d'identifiant");
        }
        if (reservationDTO.getDateFin() != null && reservationDTO.getDateDebut() != null
                && reservationDTO.getDateFin().isBefore(reservationDTO.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Reservation saved = reservationRepository.save(reservation);
        return reservationMapper.toDTO(saved);
    }

    @Override
    public ReservationDTO findById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
        return reservationMapper.toDTO(reservation);
    }

    @Override
    public List<ReservationDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toDTOList(reservations);
    }

    @Override
    public ReservationDTO update(Long id, ReservationDTO reservationDTO) {
        Reservation existant = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
        if (reservationDTO.getDateFin() != null && reservationDTO.getDateDebut() != null
                && reservationDTO.getDateFin().isBefore(reservationDTO.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }
        existant.setDateDebut(reservationDTO.getDateDebut());
        existant.setDateFin(reservationDTO.getDateFin());
        existant.setStatut(reservationDTO.getStatut());
        Reservation fromDTO = reservationMapper.toEntity(reservationDTO);
        existant.setVehicule(fromDTO.getVehicule());
        existant.setClient(fromDTO.getClient());
        Reservation updated = reservationRepository.save(existant);
        return reservationMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }
        reservationRepository.deleteById(id);
    }
}
