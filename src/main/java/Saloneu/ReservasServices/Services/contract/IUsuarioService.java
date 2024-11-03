package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    // Crear un nuevo usuario
    Usuario createUsuario(Usuario usuario);

    // Obtener todos los usuarios
    List<Usuario> getAllUsuarios();

    // Obtener un usuario por su ID
    Optional<Usuario> getUsuarioById(Integer id);

    // Actualizar un usuario existente
    Usuario updateUsuario(Integer id, Usuario usuarioDetails);

    // Eliminar un usuario por su ID
    void deleteUsuario(Integer id);
}
