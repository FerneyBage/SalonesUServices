package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaloneRepository extends JpaRepository<Salon, Integer> {
}