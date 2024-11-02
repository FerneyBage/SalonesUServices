package Saloneu.ReservasServices.Models.Dtos.MapperDto;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.TiposSalonDto.TipoSalonDTO;
import Saloneu.ReservasServices.Models.entities.TiposSalon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoSalonMapper {
    TipoSalonMapper INSTANCE = Mappers.getMapper(TipoSalonMapper.class);

    TipoSalonDTO toDto(TiposSalon tipoSalon);

    TiposSalon toEntity(TipoSalonDTO tipoSalonDTO);
}

