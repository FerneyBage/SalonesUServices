package Saloneu.ReservasServices.Models.Dtos.MapperDto;



import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.UsuarioDto.UsuarioCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.UsuarioDto.UsuarioListDTO;
import Saloneu.ReservasServices.Models.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // Convertir de entidad a DTO de lista
    @Mapping(source = "idRol.id", target = "idRol")
    UsuarioListDTO toListDTO(Usuario usuario);

    // Convertir de DTO de creación a entidad
    @Mapping(source = "idRol", target = "idRol.id")
    Usuario toEntity(UsuarioCreateDTO usuarioCreateDTO);
}