package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto;


import java.time.LocalDateTime;
import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DisponibilidadSalonCreateDTO {
    @NotNull
    private Integer idSalon;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFin;

    private Boolean visibilidad = true; // Valor por defecto es true para visibilidad

}
