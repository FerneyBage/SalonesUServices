package Saloneu.ReservasServices.Models.Dtos.EntitiesDto.SalonDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalonCreateDTO {
    private String nombre;
    private Integer capacidad;
    private String ubicacion;
    private String descripcion;
    private Integer idTipoSalon;
    private Integer idEdificio;
}
