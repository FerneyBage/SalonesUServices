package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}