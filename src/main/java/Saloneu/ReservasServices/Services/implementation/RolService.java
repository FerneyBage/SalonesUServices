package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.Role;
import Saloneu.ReservasServices.Repositories.IRoleRepository;
import Saloneu.ReservasServices.Services.contract.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {

    private final IRoleRepository rolRepository;

    @Autowired
    public RolService(IRoleRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // Crear un nuevo rol
    @Override
    public Role createRol(Role rol) {
        return rolRepository.save(rol);
    }

    // Obtener todos los roles
    @Override
    public List<Role> getAllRoles() {
        return rolRepository.findAll();
    }

    // Obtener un rol por su ID
    @Override
    public Optional<Role> getRolById(Integer id) {
        return rolRepository.findById(id);
    }

    // Actualizar un rol existente
    @Override
    public Role updateRol(Integer id, Role rolDetails) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNombreRol(rolDetails.getNombreRol());
            return rolRepository.save(rol);
        }).orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

    // Eliminar un rol por su ID
    @Override
    public void deleteRol(Integer id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rol no encontrado con ID: " + id);
        }
    }
}