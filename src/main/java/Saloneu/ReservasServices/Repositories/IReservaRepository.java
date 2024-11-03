package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByIdUsuarioId(Integer usuarioId);
}