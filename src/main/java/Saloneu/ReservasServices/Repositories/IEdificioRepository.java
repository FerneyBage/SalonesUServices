package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEdificioRepository extends JpaRepository<Edificio, Integer> {
}