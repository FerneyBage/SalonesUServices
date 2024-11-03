package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DisponibilidadSalonListDTO {
    private Integer id;
    private Integer idSalon;
    private LocalTime  horaInicio;
    private LocalTime  horaFin;
    private Boolean visibilidad;

    // Getters y Setters
}
