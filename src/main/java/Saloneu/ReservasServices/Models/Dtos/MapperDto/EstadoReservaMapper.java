package Saloneu.ReservasServices.Models.Dtos.MapperDto;


import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EstadoReservaDto.EstadoReservaCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EstadoReservaDto.EstadoReservaListDTO;
import Saloneu.ReservasServices.Models.entities.EstadoReserva;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstadoReservaMapper {
    EstadoReservaMapper INSTANCE = Mappers.getMapper(EstadoReservaMapper.class);

    // Convertir de entidad a DTO de lista
    EstadoReservaListDTO toListDTO(EstadoReserva estadoReserva);

    // Convertir de DTO de creación a entidad
    EstadoReserva toEntity(EstadoReservaCreateDTO estadoReservaCreateDTO);
}