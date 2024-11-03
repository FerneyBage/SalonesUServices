package Saloneu.ReservasServices.Controllers;



import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EstadoReservaDto.EstadoReservaCreateDTO;
import Saloneu.ReservasServices.Models.Dtos.EntitiesDto.EstadoReservaDto.EstadoReservaListDTO;
import Saloneu.ReservasServices.Models.Dtos.MapperDto.EstadoReservaMapper;
import Saloneu.ReservasServices.Models.entities.EstadoReserva;
import Saloneu.ReservasServices.Services.contract.IEstadoReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Estados de Reserva", description = "Operaciones para gestionar los estados de reserva")
@RestController
@RequestMapping("/api/estados-reserva")
public class EstadoReservaController {

    private final IEstadoReservaService estadoReservaService;

    @Autowired
    public EstadoReservaController(IEstadoReservaService estadoReservaService) {
        this.estadoReservaService = estadoReservaService;
    }

    @Operation(summary = "Obtener todos los estados de reserva", description = "Recupera una lista de todos los estados de reserva registrados en el sistema.")
    @GetMapping
    public ResponseEntity<List<EstadoReservaListDTO>> getAllEstadosReserva() {
        List<EstadoReservaListDTO> estadosReserva = estadoReservaService.getAllEstadosReserva()
                .stream()
                .map(EstadoReservaMapper.INSTANCE::toListDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(estadosReserva);
    }

    @Operation(summary = "Obtener un estado de reserva por ID", description = "Recupera la información de un estado de reserva específico por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<EstadoReservaListDTO> getEstadoReservaById(@PathVariable Integer id) {
        return estadoReservaService.getEstadoReservaById(id)
                .map(EstadoReservaMapper.INSTANCE::toListDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo estado de reserva", description = "Crea un nuevo estado de reserva en el sistema.")
    @PostMapping
    public ResponseEntity<EstadoReservaListDTO> createEstadoReserva(@RequestBody EstadoReservaCreateDTO estadoReservaCreateDTO) {
        EstadoReserva estadoReserva = EstadoReservaMapper.INSTANCE.toEntity(estadoReservaCreateDTO);
        EstadoReserva savedEstadoReserva = estadoReservaService.createEstadoReserva(estadoReserva);
        EstadoReservaListDTO savedDTO = EstadoReservaMapper.INSTANCE.toListDTO(savedEstadoReserva);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un estado de reserva por ID", description = "Actualiza la información de un estado de reserva existente por su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<EstadoReservaListDTO> updateEstadoReserva(@PathVariable Integer id, @RequestBody EstadoReservaCreateDTO estadoReservaCreateDTO) {
        EstadoReserva estadoReserva = EstadoReservaMapper.INSTANCE.toEntity(estadoReservaCreateDTO);
        EstadoReserva updatedEstadoReserva = estadoReservaService.updateEstadoReserva(id, estadoReserva);
        EstadoReservaListDTO updatedDTO = EstadoReservaMapper.INSTANCE.toListDTO(updatedEstadoReserva);
        return ResponseEntity.ok(updatedDTO);
    }

    @Operation(summary = "Eliminar un estado de reserva por ID", description = "Elimina un estado de reserva específico del sistema utilizando su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoReserva(@PathVariable Integer id) {
        estadoReservaService.deleteEstadoReserva(id);
        return ResponseEntity.noContent().build();
    }
}
