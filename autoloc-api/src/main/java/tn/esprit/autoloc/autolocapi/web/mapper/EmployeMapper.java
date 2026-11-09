package tn.esprit.autoloc.autolocapi.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.web.dto.EmployeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeMapper {

  private final IAgenceRepository agenceRepository;

  public EmployeDTO toDTO(Employe employe) {
    if (employe == null) {
      return null;
    }
    return EmployeDTO.builder()
        .idEmploye(employe.getIdEmploye())
        .nom(employe.getNom())
        .prenom(employe.getPrenom())
        .role(employe.getRole())
        .agenceId(employe.getAgence() != null ? employe.getAgence().getIdAgence() : null)
        .agenceNom(employe.getAgence() != null ? employe.getAgence().getNom() : null)
        .build();
  }

  public Employe toEntity(EmployeDTO dto) {
    if (dto == null) {
      return null;
    }
    Employe employe = new Employe();
    employe.setIdEmploye(dto.getIdEmploye());
    employe.setNom(dto.getNom());
    employe.setPrenom(dto.getPrenom());
    employe.setRole(dto.getRole());
    
    if (dto.getAgenceId() != null) {
      Agence agence = agenceRepository.findById(dto.getAgenceId()).orElse(null);
      employe.setAgence(agence);
    }
    return employe;
  }

  public List<EmployeDTO> toDTOList(List<Employe> employes) {
    return employes.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
