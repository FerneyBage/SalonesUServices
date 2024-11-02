package Saloneu.ReservasServices.Controllers;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EdificiosDto.EdificioDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.EdificioMapper;
import Saloneu.ReservasServices.Models.entities.Edificio;
import Saloneu.ReservasServices.Services.contract.IEdificioService;
import Saloneu.ReservasServices.Services.implementation.EdificioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/edificios")
@Tag(name = "Edificios", description = "Operaciones relacionadas con los edificios")
public class EdificiosController {

    private final IEdificioService edificiosService;
    private final EdificioMapper mapper;

    // Inyección de dependencias por constructor
    public EdificiosController(IEdificioService edificiosService) {
        this.edificiosService = edificiosService;
        this.mapper = EdificioMapper.INSTANCE;
    }

    @Operation(summary = "Obtener todos los edificios", description = "Devuelve una lista de todos los edificios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de edificios obtenida con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EdificioDTO.class)))
    })
    @GetMapping
    public List<EdificioDTO> obtenerTodosLosEdificios() {
        return edificiosService.obtenerTodosLosEdificio()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un edificio por ID", description = "Devuelve un edificio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edificio encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EdificioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Edificio no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EdificioDTO> obtenerEdificioPorId(@PathVariable Integer id) {
        return edificiosService.obtenerEdificioPorId(id)
                .map(edificio -> ResponseEntity.ok(mapper.toDto(edificio)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo edificio", description = "Crea un nuevo edificio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edificio creado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EdificioDTO.class)))
    })
    @PostMapping
    public ResponseEntity<EdificioDTO> crearEdificio(@RequestBody EdificioDTO edificioDTO) {
        Edificio nuevoEdificio = mapper.toEntity(edificioDTO);
        Edificio edificioCreado = edificiosService.crearEdificio(nuevoEdificio);
        return ResponseEntity.ok(mapper.toDto(edificioCreado));
    }

    @Operation(summary = "Actualizar un edificio", description = "Actualiza los datos de un edificio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edificio actualizado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EdificioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Edificio no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EdificioDTO> actualizarEdificio(
            @PathVariable Integer id, @RequestBody EdificioDTO edificioDTO) {
        Edificio edificioParaActualizar = mapper.toEntity(edificioDTO);
        Edificio edificioActualizado = edificiosService.actualizarEdificio(id, edificioParaActualizar);
        return ResponseEntity.ok(mapper.toDto(edificioActualizado));
    }

    @Operation(summary = "Eliminar un edificio", description = "Elimina un edificio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Edificio eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Edificio no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEdificio(@PathVariable Integer id) {
        edificiosService.eliminarEdificio(id);
        return ResponseEntity.noContent().build();
    }
}


