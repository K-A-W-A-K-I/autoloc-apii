package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.web.dto.ContratDTO;

public interface IContratService {

    ContratDTO create(ContratDTO contratDTO);

    ContratDTO findById(Long id);

    List<ContratDTO> findAll();

    ContratDTO update(Long id, ContratDTO contratDTO);

    void deleteById(Long id);
}
