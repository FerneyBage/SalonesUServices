package Saloneu.ReservasServices.Controllers;



import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.UsuarioDto.UsuarioCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.UsuarioDto.UsuarioListDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.UsuarioMapper;
import Saloneu.ReservasServices.Models.entities.Usuario;
import Saloneu.ReservasServices.Services.contract.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Usuarios", description = "Operaciones para gestionar los usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Recupera una lista de todos los usuarios registrados en el sistema.")
    @GetMapping
    public ResponseEntity<List<UsuarioListDTO>> getAllUsuarios() {
        List<UsuarioListDTO> usuarios = usuarioService.getAllUsuarios()
                .stream()
                .map(UsuarioMapper.INSTANCE::toListDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Recupera la información de un usuario específico por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListDTO> getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getUsuarioById(id)
                .map(UsuarioMapper.INSTANCE::toListDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema. Verifica que el rol asociado exista.")
    @PostMapping
    public ResponseEntity<UsuarioListDTO> createUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioCreateDTO);
        Usuario savedUsuario = usuarioService.createUsuario(usuario);
        UsuarioListDTO savedDTO = UsuarioMapper.INSTANCE.toListDTO(savedUsuario);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un usuario por ID", description = "Actualiza la información de un usuario existente por su ID. Verifica que el rol asociado exista.")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioListDTO> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioCreateDTO);
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        UsuarioListDTO updatedDTO = UsuarioMapper.INSTANCE.toListDTO(updatedUsuario);
        return ResponseEntity.ok(updatedDTO);
    }

    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario específico del sistema utilizando su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
