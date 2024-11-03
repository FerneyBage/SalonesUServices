package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaCreateDTO {
    @NotNull
    private Integer idUsuario;

    @NotNull
    private Integer idSalon;

    @NotNull
    private LocalDateTime fecha; // Fecha y hora de la reserva

    @NotNull
    private Integer idDisponibilidad;
}
