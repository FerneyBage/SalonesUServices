package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto;


import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DisponibilidadSalonCreateDTO {
    @NotNull
    private Integer idSalon;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "UTC")
    private LocalTime horaInicio;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "UTC")
    private LocalTime horaFin;

    private Boolean visibilidad = true; // Valor por defecto es true para visibilidad

}
