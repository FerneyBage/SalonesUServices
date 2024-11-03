package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaEstadoUpdateDTO {

    @NotNull
    private Integer reservaId;

    @NotNull
    private Integer estadoId;
}