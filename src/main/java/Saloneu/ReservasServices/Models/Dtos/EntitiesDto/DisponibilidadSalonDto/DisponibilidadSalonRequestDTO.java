package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DisponibilidadSalonRequestDTO {

    @NotNull
    private Integer salonId;

    @NotNull
    private LocalDate fecha;
}
