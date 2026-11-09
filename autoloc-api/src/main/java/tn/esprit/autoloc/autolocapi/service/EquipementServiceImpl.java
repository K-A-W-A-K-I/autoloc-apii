package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;
import tn.esprit.autoloc.autolocapi.web.dto.EquipementDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.EquipementMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;
    private final EquipementMapper equipementMapper;

    @Override
    public EquipementDTO create(EquipementDTO equipementDTO) {
        if (equipementDTO.getIdEquipement() != null) {
            throw new IllegalArgumentException(
                    "Un nouvel équipement ne doit pas avoir d'identifiant");
        }
        if (equipementDTO.getLibelle() == null || equipementDTO.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }
        Equipement equipement = equipementMapper.toEntity(equipementDTO);
        Equipement saved = equipementRepository.save(equipement);
        return equipementMapper.toDTO(saved);
    }

    @Override
    public EquipementDTO findById(Long id) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
        return equipementMapper.toDTO(equipement);
    }

    @Override
    public List<EquipementDTO> findAll() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipementMapper.toDTOList(equipements);
    }

    @Override
    public EquipementDTO update(Long id, EquipementDTO equipementDTO) {
        Equipement existant = equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
        if (equipementDTO.getLibelle() == null || equipementDTO.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }
        existant.setLibelle(equipementDTO.getLibelle());
        Equipement updated = equipementRepository.save(existant);
        return equipementMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }
        equipementRepository.deleteById(id);
    }
}
