package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.domain.RoleEmploye;

import java.util.List;

public interface IEmployeService {
    List<Employe> retrieveAllEmployes();
    Employe retrieveEmploye(Long idEmploye);
    Employe addEmploye(Employe employe);
    Employe updateEmploye(Employe employe);
    void removeEmploye(Long idEmploye);
    List<Employe> findByRole(RoleEmploye role);
}
