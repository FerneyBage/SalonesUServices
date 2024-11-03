package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.UsuarioDto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioListDTO {
    private Integer id;
    private String nombre;
    private String correo;
    private String telefono;
    private Boolean activo;
    private Integer idRol;

    // Getters y Setters
}