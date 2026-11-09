package tn.esprit.autoloc.autolocapi.web.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.web.dto.EquipementDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipementMapper {

  public EquipementDTO toDTO(Equipement equipement) {
    if (equipement == null) {
      return null;
    }
    return EquipementDTO.builder()
        .idEquipement(equipement.getIdEquipement())
        .libelle(equipement.getLibelle())
        .build();
  }

  public Equipement toEntity(EquipementDTO dto) {
    if (dto == null) {
      return null;
    }
    Equipement equipement = new Equipement();
    equipement.setIdEquipement(dto.getIdEquipement());
    equipement.setLibelle(dto.getLibelle());
    return equipement;
  }

  public List<EquipementDTO> toDTOList(List<Equipement> equipements) {
    return equipements.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
