package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.RoleDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolCreateDTO {
    @NotNull
    private String nombreRol;

    // Getters y Setters
}
