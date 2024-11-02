package Saloneu.ReservasServices.Controllers;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.TiposSalonDto.TipoSalonDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.TipoSalonMapper;
import Saloneu.ReservasServices.Models.entities.TiposSalon;
import Saloneu.ReservasServices.Services.contract.ITipoSalonService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos-salon")
@Tag(name = "Tipo de Salón", description = "Operaciones relacionadas con el manejo de tipos de salón")
public class TipoSalonController {

    private final ITipoSalonService tipoSalonService;
    private final TipoSalonMapper mapper = TipoSalonMapper.INSTANCE;

    @Autowired
    public TipoSalonController(ITipoSalonService tipoSalonService) {
        this.tipoSalonService = tipoSalonService;
    }

    @Operation(summary = "Obtener todos los tipos de salón", description = "Devuelve una lista de todos los tipos de salón disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tipos de salón obtenida con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalonDTO.class)))
    })
    @GetMapping
    public List<TipoSalonDTO> obtenerTodosLosTiposSalon() {
        return tipoSalonService.obtenerTodosLosTiposSalon()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un tipo de salón por ID", description = "Devuelve un tipo de salón específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de salón encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalonDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de salón no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoSalonDTO> obtenerTipoSalonPorId(@PathVariable Integer id) {
        return tipoSalonService.obtenerTiposSalonPorId(id)
                .map(tipoSalon -> ResponseEntity.ok(mapper.toDto(tipoSalon)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo tipo de salón", description = "Crea un nuevo tipo de salón en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de salón creado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalonDTO.class)))
    })
    @PostMapping
    public ResponseEntity<TipoSalonDTO> crearTipoSalon(@RequestBody TipoSalonDTO tipoSalonDTO) {
        TiposSalon tipoSalon = mapper.toEntity(tipoSalonDTO);
        TiposSalon tipoSalonCreado = tipoSalonService.crearTiposSalon(tipoSalon);
        return ResponseEntity.ok(mapper.toDto(tipoSalonCreado));
    }

    @Operation(summary = "Actualizar un tipo de salón", description = "Actualiza los datos de un tipo de salón existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de salón actualizado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalonDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de salón no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoSalonDTO> actualizarTipoSalon(
            @PathVariable Integer id, @RequestBody TipoSalonDTO tipoSalonDTO) {
        TiposSalon tipoSalonParaActualizar = mapper.toEntity(tipoSalonDTO);
        TiposSalon tipoSalonActualizado = tipoSalonService.actualizarTiposSalon(id, tipoSalonParaActualizar);
        return ResponseEntity.ok(mapper.toDto(tipoSalonActualizado));
    }

    @Operation(summary = "Eliminar un tipo de salón", description = "Elimina un tipo de salón específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de salón eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Tipo de salón no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoSalon(@PathVariable Integer id) {
        tipoSalonService.eliminarTiposSalon(id);
        return ResponseEntity.noContent().build();
    }
}

