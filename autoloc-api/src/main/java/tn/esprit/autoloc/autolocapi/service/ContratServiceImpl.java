package tn.esprit.autoloc.autolocapi.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.web.dto.ContratDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.ContratMapper;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final ContratMapper contratMapper;

    @Override
    public ContratDTO create(ContratDTO contratDTO) {
        if (contratDTO.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        if (contratDTO.getMontantTotal() != null && contratDTO.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        Contrat contrat = contratMapper.toEntity(contratDTO);
        // Lier les paiements au contrat (cascade)
        if (contrat.getPaiements() != null) {
            contrat.getPaiements().forEach(paiement -> paiement.setContrat(contrat));
        }
        Contrat saved = contratRepository.save(contrat);
        return contratMapper.toDTO(saved);
    }

    @Override
    public ContratDTO findById(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        return contratMapper.toDTO(contrat);
    }

    @Override
    public List<ContratDTO> findAll() {
        List<Contrat> contrats = contratRepository.findAll();
        return contratMapper.toDTOList(contrats);
    }

    @Override
    public ContratDTO update(Long id, ContratDTO contratDTO) {
        Contrat existant = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        if (contratDTO.getMontantTotal() != null && contratDTO.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        existant.setMontantTotal(contratDTO.getMontantTotal());
        existant.setDateSignature(contratDTO.getDateSignature());
        existant.setValide(contratDTO.isValide());
        Contrat fromDTO = contratMapper.toEntity(contratDTO);
        existant.setReservation(fromDTO.getReservation());
        Contrat updated = contratRepository.save(existant);
        return contratMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }
}
