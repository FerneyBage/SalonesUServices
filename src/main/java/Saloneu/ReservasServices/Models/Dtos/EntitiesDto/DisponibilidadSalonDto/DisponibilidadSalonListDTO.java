package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DisponibilidadSalonListDTO {
    private Integer id;
    private Integer idSalon;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "UTC")
    private LocalTime  horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "UTC")
    private LocalTime  horaFin;
    private Boolean visibilidad;

    // Getters y Setters
}
