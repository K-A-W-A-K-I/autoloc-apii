package tn.esprit.autoloc.autolocapi.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;
import tn.esprit.autoloc.autolocapi.web.dto.VehiculeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VehiculeMapper {

  private final IAgenceRepository agenceRepository;
  private final IEquipementRepository equipementRepository;

  public VehiculeDTO toDTO(Vehicule vehicule) {
    if (vehicule == null) {
      return null;
    }
    return VehiculeDTO.builder()
        .idVehicule(vehicule.getIdVehicule())
        .immatriculation(vehicule.getImmatriculation())
        .marque(vehicule.getMarque())
        .modele(vehicule.getModele())
        .categorie(vehicule.getCategorie())
        .tarifJournalier(vehicule.getTarifJournalier())
        .statut(vehicule.getStatut())
        .agenceId(vehicule.getAgence() != null ? vehicule.getAgence().getIdAgence() : null)
        .agenceNom(vehicule.getAgence() != null ? vehicule.getAgence().getNom() : null)
        .equipementIds(vehicule.getEquipements() != null ? 
            vehicule.getEquipements().stream()
                .map(Equipement::getIdEquipement)
                .collect(Collectors.toList()) : null)
        .build();
  }

  public Vehicule toEntity(VehiculeDTO dto) {
    if (dto == null) {
      return null;
    }
    Vehicule vehicule = new Vehicule();
    vehicule.setIdVehicule(dto.getIdVehicule());
    vehicule.setImmatriculation(dto.getImmatriculation());
    vehicule.setMarque(dto.getMarque());
    vehicule.setModele(dto.getModele());
    vehicule.setCategorie(dto.getCategorie());
    vehicule.setTarifJournalier(dto.getTarifJournalier());
    vehicule.setStatut(dto.getStatut());
    
    if (dto.getAgenceId() != null) {
      Agence agence = agenceRepository.findById(dto.getAgenceId()).orElse(null);
      vehicule.setAgence(agence);
    }
    
    if (dto.getEquipementIds() != null) {
      List<Equipement> equipements = equipementRepository.findAllById(dto.getEquipementIds());
      vehicule.setEquipements(equipements);
    }
    
    return vehicule;
  }

  public List<VehiculeDTO> toDTOList(List<Vehicule> vehicules) {
    return vehicules.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
