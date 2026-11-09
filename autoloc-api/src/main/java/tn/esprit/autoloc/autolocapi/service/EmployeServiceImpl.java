package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;
import tn.esprit.autoloc.autolocapi.web.dto.EmployeDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.EmployeMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;
    private final EmployeMapper employeMapper;

    @Override
    public EmployeDTO create(EmployeDTO employeDTO) {
        if (employeDTO.getIdEmploye() != null) {
            throw new IllegalArgumentException(
                    "Un nouvel employé ne doit pas avoir d'identifiant");
        }
        if (employeDTO.getNom() == null || employeDTO.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        if (employeDTO.getPrenom() == null || employeDTO.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom de l'employé est obligatoire");
        }
        if (employeDTO.getRole() == null) {
            throw new IllegalArgumentException("Le rôle de l'employé est obligatoire");
        }
        Employe employe = employeMapper.toEntity(employeDTO);
        Employe saved = employeRepository.save(employe);
        return employeMapper.toDTO(saved);
    }

    @Override
    public EmployeDTO findById(Long id) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
        return employeMapper.toDTO(employe);
    }

    @Override
    public List<EmployeDTO> findAll() {
        List<Employe> employes = employeRepository.findAll();
        return employeMapper.toDTOList(employes);
    }

    @Override
    public EmployeDTO update(Long id, EmployeDTO employeDTO) {
        Employe existant = employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
        if (employeDTO.getNom() == null || employeDTO.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        if (employeDTO.getPrenom() == null || employeDTO.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom de l'employé est obligatoire");
        }
        if (employeDTO.getRole() == null) {
            throw new IllegalArgumentException("Le rôle de l'employé est obligatoire");
        }
        existant.setNom(employeDTO.getNom());
        existant.setPrenom(employeDTO.getPrenom());
        existant.setRole(employeDTO.getRole());
        Employe fromDTO = employeMapper.toEntity(employeDTO);
        existant.setAgence(fromDTO.getAgence());
        Employe updated = employeRepository.save(existant);
        return employeMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        employeRepository.deleteById(id);
    }
}
