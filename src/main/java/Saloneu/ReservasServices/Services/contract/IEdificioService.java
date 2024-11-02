package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.Edificio;

import java.util.List;
import java.util.Optional;

public interface IEdificioService {
    Edificio crearEdificio(Edificio edificio);
    Optional<Edificio> obtenerEdificioPorId(Integer id);
    List<Edificio> obtenerTodosLosEdificio();
    void eliminarEdificio(Integer id);
    Edificio actualizarEdificio(Integer id, Edificio detallesEdificio);


}
