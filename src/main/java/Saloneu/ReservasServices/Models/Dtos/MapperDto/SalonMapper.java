package Saloneu.ReservasServices.Models.Dtos.MapperDto;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.SalonDto.SalonCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.SalonDto.SalonDTO;
import Saloneu.ReservasServices.Models.entities.Edificio;
import Saloneu.ReservasServices.Models.entities.Salon;
import Saloneu.ReservasServices.Models.entities.TiposSalon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SalonMapper {
    SalonMapper INSTANCE = Mappers.getMapper(SalonMapper.class);

    // Convierte de entidad a DTO para listar o mostrar detalles
    @Mapping(source = "tipoSalon", target = "idTipoSalon", qualifiedByName = "tipoSalonToId")
    @Mapping(source = "edificio", target = "idEdificio", qualifiedByName = "edificioToId")
    SalonDTO toDto(Salon salon);

    // Convierte de DTO para creación a entidad
    @Mapping(source = "idTipoSalon", target = "tipoSalon", qualifiedByName = "idToTipoSalon")
    @Mapping(source = "idEdificio", target = "edificio", qualifiedByName = "idToEdificio")
    Salon toEntity(SalonCreateDTO salonCreateDTO);

    // Método para convertir TipoSalon a su ID en SalonDTO
    @Named("tipoSalonToId")
    default Integer tipoSalonToId(TiposSalon tipoSalon) {
        return tipoSalon != null ? tipoSalon.getId() : null;
    }

    // Método para convertir Edificio a su ID en SalonDTO
    @Named("edificioToId")
    default Integer edificioToId(Edificio edificio) {
        return edificio != null ? edificio.getId() : null;
    }

    // Método para convertir un idTipoSalon a un objeto TipoSalon (para toEntity)
    @Named("idToTipoSalon")
    default TiposSalon idToTipoSalon(Integer idTipoSalon) {
        if (idTipoSalon == null) {
            return null;
        }
        TiposSalon tipoSalon = new TiposSalon();
        tipoSalon.setId(idTipoSalon);
        return tipoSalon;
    }

    // Método para convertir un idEdificio a un objeto Edificio (para toEntity)
    @Named("idToEdificio")
    default Edificio idToEdificio(Integer idEdificio) {
        if (idEdificio == null) {
            return null;
        }
        Edificio edificio = new Edificio();
        edificio.setId(idEdificio);
        return edificio;
    }
}
