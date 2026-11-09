package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.web.dto.AgenceDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.AgenceMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public AgenceDTO create(AgenceDTO agenceDTO) {
        if (agenceDTO.getIdAgence() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle agence ne doit pas avoir d'identifiant");
        }
        if (agenceDTO.getNom() == null || agenceDTO.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
        if (agenceDTO.getVille() == null || agenceDTO.getVille().trim().isEmpty()) {
            throw new IllegalArgumentException("La ville de l'agence est obligatoire");
        }
        Agence agence = agenceMapper.toEntity(agenceDTO);
        Agence saved = agenceRepository.save(agence);
        return agenceMapper.toDTO(saved);
    }

    @Override
    public AgenceDTO findById(Long id) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
        return agenceMapper.toDTO(agence);
    }

    @Override
    public List<AgenceDTO> findAll() {
        List<Agence> agences = agenceRepository.findAll();
        return agenceMapper.toDTOList(agences);
    }

    @Override
    public AgenceDTO update(Long id, AgenceDTO agenceDTO) {
        Agence existant = agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
        if (agenceDTO.getNom() == null || agenceDTO.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
        if (agenceDTO.getVille() == null || agenceDTO.getVille().trim().isEmpty()) {
            throw new IllegalArgumentException("La ville de l'agence est obligatoire");
        }
        existant.setNom(agenceDTO.getNom());
        existant.setVille(agenceDTO.getVille());
        existant.setAdresse(agenceDTO.getAdresse());
        existant.setTelephone(agenceDTO.getTelephone());
        Agence updated = agenceRepository.save(existant);
        return agenceMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }
        agenceRepository.deleteById(id);
    }
}
