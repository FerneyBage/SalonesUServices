package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EstadoReservaDto.EstadoReservaListDTO;
import Saloneu.ReservasServices.Models.entities.EstadoReserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaListDTO {
    private Integer id;
    private Integer idUsuario;
    private Integer idSalon;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime fecha; // Fecha y hora de la reserva
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant fechaCreacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalTime horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalTime horaFin;
    private EstadoReservaListDTO idEstado;
    private Integer idDisponibilidad;
}
