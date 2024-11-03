package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    // Crear un nuevo rol
    Role createRol(Role rol);

    // Obtener todos los roles
    List<Role> getAllRoles();

    // Obtener un rol por su ID
    Optional<Role> getRolById(Integer id);

    // Actualizar un rol existente
    Role updateRol(Integer id, Role rolDetails);

    // Eliminar un rol por su ID
    void deleteRol(Integer id);
}
