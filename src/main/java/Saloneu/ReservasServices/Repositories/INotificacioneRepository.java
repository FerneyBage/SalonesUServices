package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Notificacione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificacioneRepository extends JpaRepository<Notificacione, Integer> {
}