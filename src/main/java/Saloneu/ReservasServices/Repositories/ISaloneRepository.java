package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Salone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaloneRepository extends JpaRepository<Salone, Integer> {
}