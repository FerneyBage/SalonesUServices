package Saloneu.ReservasServices.Models.Dtos.MapperDto;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.RoleDTO.RolCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.RoleDTO.RolListDTO;
import Saloneu.ReservasServices.Models.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    // Convertir de entidad a DTO de lista
    RolListDTO toListDTO(Role rol);

    // Convertir de DTO de creación a entidad
    Role toEntity(RolCreateDTO rolCreateDTO);
}
