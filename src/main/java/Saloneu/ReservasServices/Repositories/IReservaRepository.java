package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservaRepository extends JpaRepository<Reserva, Integer> {
}