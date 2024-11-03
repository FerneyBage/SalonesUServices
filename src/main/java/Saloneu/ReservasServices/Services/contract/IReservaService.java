package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.Reserva;

import java.util.List;

public interface IReservaService {
    Reserva crearReserva(Reserva reserva);

    List<Reserva> getReservasByUsuarioId(Integer usuarioId);

    Reserva actualizarEstadoReserva(Integer reservaId, Integer estadoId);
}
