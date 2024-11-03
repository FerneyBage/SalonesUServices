package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.Usuario;
import Saloneu.ReservasServices.Repositories.IUsuarioRepository;
import Saloneu.ReservasServices.Repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements Saloneu.ReservasServices.Services.contract.IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IRoleRepository roleRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository, IRoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    // Crear un nuevo usuario
    @Override
    public Usuario createUsuario(Usuario usuario) {
        // Verificar que el rol exista
        if (!roleRepository.existsById(usuario.getIdRol().getId())) {
            throw new RuntimeException("El rol con ID " + usuario.getIdRol().getId() + " no existe.");
        }
        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios
    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por su ID
    @Override
    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Actualizar un usuario existente
    @Override
    public Usuario updateUsuario(Integer id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            // Verificar que el rol exista antes de actualizar
            if (!roleRepository.existsById(usuarioDetails.getIdRol().getId())) {
                throw new RuntimeException("El rol con ID " + usuarioDetails.getIdRol().getId() + " no existe.");
            }
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setTelefono(usuarioDetails.getTelefono());
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setContraseña(usuarioDetails.getContraseña());
            usuario.setActivo(usuarioDetails.getActivo());
            usuario.setIdRol(usuarioDetails.getIdRol());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // Eliminar un usuario por su ID
    @Override
    public void deleteUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
