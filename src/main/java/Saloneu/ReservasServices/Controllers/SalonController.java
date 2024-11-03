package Saloneu.ReservasServices.Controllers;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.SalonDto.SalonCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.SalonDto.SalonDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.SalonMapper;
import Saloneu.ReservasServices.Models.entities.Salon;
import Saloneu.ReservasServices.Services.contract.ISalonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/salones")
@Tag(name = "Salones", description = "Operaciones relacionadas con los salones")
public class SalonController {

    private final ISalonService salonService;
    private final SalonMapper mapper = SalonMapper.INSTANCE;

    @Autowired
    public SalonController(ISalonService salonService) {
        this.salonService = salonService;
    }

    @Operation(summary = "Obtener todos los salones", description = "Devuelve una lista de todos los salones disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de salones obtenida con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalonDTO.class)))
    })
    @GetMapping
    public List<SalonDTO> obtenerTodosLosSalones() {
        return salonService.obtenerTodosLosSalones()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un salón por ID", description = "Devuelve un salón específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salón encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalonDTO.class))),
            @ApiResponse(responseCode = "404", description = "Salón no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> obtenerSalonPorId(@PathVariable Integer id) {
        Optional<Salon> salon = salonService.obtenerSalonPorId(id);
        return salon.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo salón", description = "Crea un nuevo salón en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salón creado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalonDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<SalonDTO> crearSalon(@RequestBody SalonCreateDTO salonCreateDTO) {
        Salon salon = mapper.toEntity(salonCreateDTO);
        Salon salonCreado = salonService.crearSalon(salon);
        return ResponseEntity.ok(mapper.toDto(salonCreado));
    }

    @Operation(summary = "Actualizar un salón existente", description = "Actualiza los datos de un salón existente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salón actualizado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalonDTO.class))),
            @ApiResponse(responseCode = "404", description = "Salón no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SalonDTO> actualizarSalon(@PathVariable Integer id, @RequestBody SalonCreateDTO detallesSalon) {
        Salon salonParaActualizar = mapper.toEntity(detallesSalon);
        Salon salonActualizado = salonService.actualizarSalon(id, salonParaActualizar);
        return ResponseEntity.ok(mapper.toDto(salonActualizado));
    }

    @Operation(summary = "Eliminar un salón", description = "Elimina un salón específico del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salón eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Salón no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSalon(@PathVariable Integer id) {
        salonService.eliminarSalon(id);
        return ResponseEntity.noContent().build();
    }
}
