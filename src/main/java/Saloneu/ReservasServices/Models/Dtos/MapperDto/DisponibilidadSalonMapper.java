package Saloneu.ReservasServices.Models.Dtos.MapperDto;


import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto.DisponibilidadSalonCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto.DisponibilidadSalonListDTO;
import Saloneu.ReservasServices.Models.entities.DisponibilidadSalon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DisponibilidadSalonMapper {
    DisponibilidadSalonMapper INSTANCE = Mappers.getMapper(DisponibilidadSalonMapper.class);

    // Convertir de entidad a DTO de lista
    @Mapping(source = "idSalon.id", target = "idSalon")
    DisponibilidadSalonListDTO toListDTO(DisponibilidadSalon disponibilidadSalon);

    // Convertir de DTO de creación a entidad
    @Mapping(source = "idSalon", target = "idSalon.id")
    DisponibilidadSalon toEntity(DisponibilidadSalonCreateDTO disponibilidadSalonCreateDTO);
}
