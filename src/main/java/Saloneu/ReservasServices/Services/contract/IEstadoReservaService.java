package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.EstadoReserva;

import java.util.List;
import java.util.Optional;

public interface IEstadoReservaService {
    // Crear un nuevo estado de reserva
    EstadoReserva createEstadoReserva(EstadoReserva estadoReserva);

    // Obtener todos los estados de reserva
    List<EstadoReserva> getAllEstadosReserva();

    // Obtener un estado de reserva por su ID
    Optional<EstadoReserva> getEstadoReservaById(Integer id);

    // Actualizar un estado de reserva existente
    EstadoReserva updateEstadoReserva(Integer id, EstadoReserva estadoReservaDetails);

    // Eliminar un estado de reserva por su ID
    void deleteEstadoReserva(Integer id);
}
