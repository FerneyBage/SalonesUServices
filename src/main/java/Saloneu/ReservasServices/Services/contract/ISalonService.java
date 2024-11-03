package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.Salon;

import java.util.List;
import java.util.Optional;

public interface ISalonService {

    // Método para crear un nuevo salón
    Salon crearSalon(Salon salon);

    // Método para obtener un salón por su ID
    Optional<Salon> obtenerSalonPorId(Integer id);

    // Método para obtener todos los Salon
    List<Salon> obtenerTodosLosSalones();

    // Método para actualizar un salón existente
    Salon actualizarSalon(Integer id, Salon detallesSalon);

    // Método para eliminar un salón por su ID
    void eliminarSalon(Integer id);
}
