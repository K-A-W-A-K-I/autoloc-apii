package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;
import tn.esprit.autoloc.autolocapi.web.dto.VehiculeDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.VehiculeMapper;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public VehiculeDTO create(VehiculeDTO vehiculeDTO) {
        if (vehiculeDTO.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        if (vehiculeDTO.getTarifJournalier() != null && vehiculeDTO.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeDTO);
        Vehicule saved = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDTO(saved);
    }

    @Override
    public VehiculeDTO findById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
        return vehiculeMapper.toDTO(vehicule);
    }

    @Override
    public List<VehiculeDTO> findAll() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        return vehiculeMapper.toDTOList(vehicules);
    }

    @Override
    public VehiculeDTO update(Long id, VehiculeDTO vehiculeDTO) {
        Vehicule existant = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
        if (vehiculeDTO.getTarifJournalier() != null && vehiculeDTO.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
        existant.setImmatriculation(vehiculeDTO.getImmatriculation());
        existant.setMarque(vehiculeDTO.getMarque());
        existant.setModele(vehiculeDTO.getModele());
        existant.setCategorie(vehiculeDTO.getCategorie());
        existant.setTarifJournalier(vehiculeDTO.getTarifJournalier());
        existant.setStatut(vehiculeDTO.getStatut());
        Vehicule fromDTO = vehiculeMapper.toEntity(vehiculeDTO);
        existant.setAgence(fromDTO.getAgence());
        existant.setEquipements(fromDTO.getEquipements());
        Vehicule updated = vehiculeRepository.save(existant);
        return vehiculeMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }
}
