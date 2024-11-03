package Saloneu.ReservasServices.Models.Dtos.MapperDto;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto.ReservaCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto.ReservaListDTO;
import Saloneu.ReservasServices.Models.entities.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservaMapper {
    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    // Mapea Reserva a ReservaListDTO
    @Mapping(source = "idUsuario.id", target = "idUsuario")
    @Mapping(source = "idSalon.id", target = "idSalon")
    @Mapping(source = "idDisponibilidad.id", target = "idDisponibilidad")
    ReservaListDTO toListDTO(Reserva reserva);

    // Mapea ReservaCreateDTO a Reserva
    @Mapping(source = "idUsuario", target = "idUsuario.id")
    @Mapping(source = "idSalon", target = "idSalon.id")
    @Mapping(source = "idDisponibilidad", target = "idDisponibilidad.id")
    Reserva toEntity(ReservaCreateDTO reservaCreateDTO);

    // Mapea Reserva a ReservaCreateDTO (si necesitas mostrar datos de creación)
    @Mapping(source = "idUsuario.id", target = "idUsuario")
    @Mapping(source = "idSalon.id", target = "idSalon")
    @Mapping(source = "idDisponibilidad.id", target = "idDisponibilidad")
    ReservaCreateDTO toCreateDTO(Reserva reserva);
}
