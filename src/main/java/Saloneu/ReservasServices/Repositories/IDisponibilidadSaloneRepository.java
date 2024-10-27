package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.DisponibilidadSalone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDisponibilidadSaloneRepository extends JpaRepository<DisponibilidadSalone, Integer> {
}