package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.TiposSalon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITiposSalonRepository extends JpaRepository<TiposSalon, Integer> {
}