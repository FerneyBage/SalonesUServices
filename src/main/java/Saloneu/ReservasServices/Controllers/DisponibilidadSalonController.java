package Saloneu.ReservasServices.Controllers;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto.DisponibilidadSalonCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto.DisponibilidadSalonListDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.DisponibilidadSalonDto.DisponibilidadSalonRequestDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.DisponibilidadSalonMapper;
import Saloneu.ReservasServices.Models.entities.DisponibilidadSalon;
import Saloneu.ReservasServices.Services.contract.IDisponibilidadSalonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Disponibilidad de Salones", description = "Operaciones para gestionar la disponibilidad de los salones")
@RestController
@RequestMapping("/api/disponibilidad-salones")
public class DisponibilidadSalonController {

    private final IDisponibilidadSalonService disponibilidadSalonesService;

    @Autowired
    public DisponibilidadSalonController(IDisponibilidadSalonService disponibilidadSalonesService) {
        this.disponibilidadSalonesService = disponibilidadSalonesService;
    }

    @Operation(summary = "Obtener todas las disponibilidades de salones")
    @GetMapping
    public ResponseEntity<List<DisponibilidadSalonListDTO>> getAllDisponibilidades() {
        List<DisponibilidadSalonListDTO> disponibilidades = disponibilidadSalonesService.getAllDisponibilidades()
                .stream()
                .map(DisponibilidadSalonMapper.INSTANCE::toListDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(disponibilidades);
    }

    @Operation(summary = "Obtener una disponibilidad de salón por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadSalonListDTO> getDisponibilidadById(@PathVariable Integer id) {
        return disponibilidadSalonesService.getDisponibilidadById(id)
                .map(DisponibilidadSalonMapper.INSTANCE::toListDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva disponibilidad de salón")
    @PostMapping
    public ResponseEntity<DisponibilidadSalonListDTO> createDisponibilidad(@RequestBody DisponibilidadSalonCreateDTO disponibilidadSalonCreateDTO) {
        DisponibilidadSalon disponibilidadSalon = DisponibilidadSalonMapper.INSTANCE.toEntity(disponibilidadSalonCreateDTO);
        DisponibilidadSalon savedDisponibilidad = disponibilidadSalonesService.createDisponibilidad(disponibilidadSalon);
        DisponibilidadSalonListDTO savedDTO = DisponibilidadSalonMapper.INSTANCE.toListDTO(savedDisponibilidad);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una disponibilidad de salón por ID")
    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadSalonListDTO> updateDisponibilidad(@PathVariable Integer id, @RequestBody DisponibilidadSalonCreateDTO disponibilidadSalonCreateDTO) {
        DisponibilidadSalon disponibilidadSalon = DisponibilidadSalonMapper.INSTANCE.toEntity(disponibilidadSalonCreateDTO);
        DisponibilidadSalon updatedDisponibilidad = disponibilidadSalonesService.updateDisponibilidad(id, disponibilidadSalon);
        DisponibilidadSalonListDTO updatedDTO = DisponibilidadSalonMapper.INSTANCE.toListDTO(updatedDisponibilidad);
        return ResponseEntity.ok(updatedDTO);
    }

    @Operation(summary = "Eliminar una disponibilidad de salón por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable Integer id) {
        disponibilidadSalonesService.deleteDisponibilidad(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener disponibilidad de un salón para una fecha específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o el salón no existe")
    })
    @PostMapping("/disponibles-para-reserva")
    public ResponseEntity<List<DisponibilidadSalonListDTO>> disponiblesParaReserva(@RequestBody DisponibilidadSalonRequestDTO disponibilidadRequest) {
        List<DisponibilidadSalonListDTO> disponibilidades = disponibilidadSalonesService.disponiblesParaReserva(
                disponibilidadRequest.getSalonId(),
                disponibilidadRequest.getFecha()).stream()
                .map(DisponibilidadSalonMapper.INSTANCE::toListDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(disponibilidades);

    }
}
