package tn.esprit.autoloc.autolocapi.web.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.web.dto.AgenceDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgenceMapper {

  public AgenceDTO toDTO(Agence agence) {
    if (agence == null) {
      return null;
    }
    return AgenceDTO.builder()
        .idAgence(agence.getIdAgence())
        .nom(agence.getNom())
        .ville(agence.getVille())
        .adresse(agence.getAdresse())
        .telephone(agence.getTelephone())
        .build();
  }

  public Agence toEntity(AgenceDTO dto) {
    if (dto == null) {
      return null;
    }
    Agence agence = new Agence();
    agence.setIdAgence(dto.getIdAgence());
    agence.setNom(dto.getNom());
    agence.setVille(dto.getVille());
    agence.setAdresse(dto.getAdresse());
    agence.setTelephone(dto.getTelephone());
    return agence;
  }

  public List<AgenceDTO> toDTOList(List<Agence> agences) {
    return agences.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
