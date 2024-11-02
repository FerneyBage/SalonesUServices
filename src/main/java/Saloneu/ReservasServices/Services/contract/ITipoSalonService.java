package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.TiposSalon;

import java.util.List;
import java.util.Optional;

public interface ITipoSalonService {

    TiposSalon crearTiposSalon(TiposSalon TiposSalon);
    Optional<TiposSalon> obtenerTiposSalonPorId(Integer id);
    List<TiposSalon> obtenerTodosLosTiposSalon();
    TiposSalon actualizarTiposSalon(Integer id, TiposSalon detallesTiposSalon);
    void eliminarTiposSalon(Integer id);
}
