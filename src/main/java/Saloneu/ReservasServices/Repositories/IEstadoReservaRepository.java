package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoReservaRepository extends JpaRepository<EstadoReserva, Integer> {
}