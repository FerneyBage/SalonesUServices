package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.UsuarioDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {
    @NotNull
    @Size(max = 100)
    private String nombre;

    @NotNull
    @Size(max = 100)
    private String correo;

    @NotNull
    @Size(max = 100)
    private String telefono;

    @NotNull
    @Size(max = 255)
    private String contraseña;

    private Boolean activo = true; // Valor por defecto para "activo"

    @NotNull
    private Integer idRol;

    // Getters y Setters
}
