package Saloneu.ReservasServices.Controllers;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.RoleDTO.RolCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.RoleDTO.RolListDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.RoleMapper;
import Saloneu.ReservasServices.Models.entities.Role;
import Saloneu.ReservasServices.Services.contract.IRolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Roles", description = "Operaciones para gestionar los roles")
@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final IRolService rolService;

    @Autowired
    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    @Operation(summary = "Obtener todos los roles")
    @GetMapping
    public ResponseEntity<List<RolListDTO>> getAllRoles() {
        List<RolListDTO> roles = rolService.getAllRoles()
                .stream()
                .map(RoleMapper.INSTANCE::toListDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Obtener un rol por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RolListDTO> getRolById(@PathVariable Integer id) {
        return rolService.getRolById(id)
                .map(RoleMapper.INSTANCE::toListDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo rol")
    @PostMapping
    public ResponseEntity<RolListDTO> createRol(@RequestBody RolCreateDTO rolCreateDTO) {
        Role rol = RoleMapper.INSTANCE.toEntity(rolCreateDTO);
        Role savedRol = rolService.createRol(rol);
        RolListDTO savedDTO = RoleMapper.INSTANCE.toListDTO(savedRol);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un rol por ID")
    @PutMapping("/{id}")
    public ResponseEntity<RolListDTO> updateRol(@PathVariable Integer id, @RequestBody RolCreateDTO rolCreateDTO) {
        Role rol = RoleMapper.INSTANCE.toEntity(rolCreateDTO);
        Role updatedRol = rolService.updateRol(id, rol);
        RolListDTO updatedDTO = RoleMapper.INSTANCE.toListDTO(updatedRol);
        return ResponseEntity.ok(updatedDTO);
    }

    @Operation(summary = "Eliminar un rol por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }
}
