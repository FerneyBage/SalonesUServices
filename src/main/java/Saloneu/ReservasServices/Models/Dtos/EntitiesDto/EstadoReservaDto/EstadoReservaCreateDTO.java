package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EstadoReservaDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EstadoReservaCreateDTO {
    @NotNull
    @Size(max = 50)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    // Getters y Setters
}
