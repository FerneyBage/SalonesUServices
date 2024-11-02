package Saloneu.ReservasServices.Models.Dtos.MapperDto;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EdificiosDto.EdificioDTO;
import Saloneu.ReservasServices.Models.entities.Edificio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EdificioMapper {
    EdificioMapper INSTANCE = Mappers.getMapper(EdificioMapper.class);

    EdificioDTO toDto(Edificio edificio);

    Edificio toEntity(EdificioDTO dto);
}
