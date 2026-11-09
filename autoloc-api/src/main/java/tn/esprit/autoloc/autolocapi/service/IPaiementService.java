package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.web.dto.PaiementDTO;

import java.util.List;

public interface IPaiementService {
    PaiementDTO findById(Long id);
    List<PaiementDTO> findAll();
}
