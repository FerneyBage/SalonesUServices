package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.DisponibilidadSalon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDisponibilidadSaloneRepository extends JpaRepository<DisponibilidadSalon, Integer> {
}