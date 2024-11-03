package Saloneu.ReservasServices.Controllers;

import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto.ReservaCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto.ReservaEstadoUpdateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.ReservaDto.ReservaListDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.ReservaMapper;
import Saloneu.ReservasServices.Models.entities.Reserva;
import Saloneu.ReservasServices.Services.contract.IReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Reservas", description = "Operaciones para gestionar las reservas de usuarios")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final IReservaService reservaService;

    @Autowired
    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    /**
     * Endpoint para crear una nueva reserva.
     *
     * @param reservaCreateDTO Los datos de la reserva a crear.
     * @return La reserva creada en formato DTO.
     */
    @Operation(summary = "Crear una nueva reserva", description = "Crea una reserva para un usuario en un salón específico en una fecha y hora determinada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaListDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para crear la reserva", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ReservaListDTO> crearReserva(
            @Parameter(description = "Datos de la reserva a crear", required = true)
            @RequestBody ReservaCreateDTO reservaCreateDTO) {

        // Convierte el DTO a la entidad Reserva usando ReservaMapper.INSTANCE
        Reserva reserva = ReservaMapper.INSTANCE.toEntity(reservaCreateDTO);

        // Llama al servicio para crear la reserva
        Reserva nuevaReserva = reservaService.crearReserva(reserva);

        // Convierte la entidad creada a DTO para la respuesta usando ReservaMapper.INSTANCE
        ReservaListDTO reservaListDTO = ReservaMapper.INSTANCE.toListDTO(nuevaReserva);

        return new ResponseEntity<>(reservaListDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener todas las reservas de un usuario dado su ID.
     *
     * @param usuarioId El ID del usuario.
     * @return Lista de reservas del usuario en formato DTO.
     */
    @Operation(summary = "Obtener reservas de un usuario", description = "Devuelve todas las reservas realizadas por un usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reservas del usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaListDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o sin reservas", content = @Content)
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReservaListDTO>> getReservasByUsuarioId(
            @Parameter(description = "ID del usuario para obtener sus reservas", required = true)
            @PathVariable Integer usuarioId) {

        // Llama al servicio para obtener las reservas del usuario
        List<Reserva> reservas = reservaService.getReservasByUsuarioId(usuarioId);

        // Convierte cada Reserva en ReservaListDTO para la respuesta usando ReservaMapper.INSTANCE
        List<ReservaListDTO> reservaListDTOs = reservas.stream()
                .map(ReservaMapper.INSTANCE::toListDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservaListDTOs);
    }

    @Operation(summary = "Actualizar el estado de una reserva", description = "Modifica el estado de una reserva existente si el estado especificado existe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de la reserva actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o el estado/reserva no existen")
    })
    @PutMapping("/actualizar-estado")
    public ResponseEntity<ReservaListDTO> actualizarEstadoReserva(@RequestBody ReservaEstadoUpdateDTO reservaEstadoUpdateDTO) {
        try {
            // Actualiza el estado de la reserva
            Reserva reservaActualizada = reservaService.actualizarEstadoReserva(
                    reservaEstadoUpdateDTO.getReservaId(),
                    reservaEstadoUpdateDTO.getEstadoId());

            // Mapea la entidad Reserva actualizada a ReservaListDTO usando ReservaMapper.INSTANCE
            ReservaListDTO reservaListDTO = ReservaMapper.INSTANCE.toListDTO(reservaActualizada);

            return ResponseEntity.ok(reservaListDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}