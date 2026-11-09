package tn.esprit.autoloc.autolocapi.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.RoleEmploye;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeDTO {

    private Long idEmploye;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères")
    private String prenom;

    @NotNull(message = "Le rôle est obligatoire")
    private RoleEmploye role;

    private Long agenceId;
    private String agenceNom;
}
