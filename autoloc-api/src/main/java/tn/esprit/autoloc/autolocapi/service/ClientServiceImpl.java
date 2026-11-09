package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.web.dto.ClientDTO;
import tn.esprit.autoloc.autolocapi.web.mapper.ClientMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        if (clientDTO.getIdClient() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau client ne doit pas avoir d'identifiant");
        }
        if (clientDTO.getNom() == null || clientDTO.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du client est obligatoire");
        }
        if (clientDTO.getEmail() == null || clientDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("L'email du client est obligatoire");
        }
        Client client = clientMapper.toEntity(clientDTO);
        Client saved = clientRepository.save(client);
        return clientMapper.toDTO(saved);
    }

    @Override
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        return clientMapper.toDTO(client);
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDTOList(clients);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client existant = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        if (clientDTO.getNom() == null || clientDTO.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du client est obligatoire");
        }
        if (clientDTO.getEmail() == null || clientDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("L'email du client est obligatoire");
        }
        existant.setNom(clientDTO.getNom());
        existant.setPrenom(clientDTO.getPrenom());
        existant.setEmail(clientDTO.getEmail());
        existant.setTelephone(clientDTO.getTelephone());
        existant.setNumPermis(clientDTO.getNumPermis());
        existant.setDateInscription(clientDTO.getDateInscription());
        Client updated = clientRepository.save(existant);
        return clientMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepository.deleteById(id);
    }
}
