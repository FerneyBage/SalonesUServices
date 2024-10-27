package Saloneu.ReservasServices.Repositories;

import Saloneu.ReservasServices.Models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}