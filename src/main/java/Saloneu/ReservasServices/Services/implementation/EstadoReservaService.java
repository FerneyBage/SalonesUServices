package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.EstadoReserva;
import Saloneu.ReservasServices.Repositories.IEstadoReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoReservaService implements Saloneu.ReservasServices.Services.contract.IEstadoReservaService {

    private final IEstadoReservaRepository estadoReservaRepository;

    @Autowired
    public EstadoReservaService(IEstadoReservaRepository estadoReservaRepository) {
        this.estadoReservaRepository = estadoReservaRepository;
    }

    // Crear un nuevo estado de reserva
    @Override
    public EstadoReserva createEstadoReserva(EstadoReserva estadoReserva) {
        return estadoReservaRepository.save(estadoReserva);
    }

    // Obtener todos los estados de reserva
    @Override
    public List<EstadoReserva> getAllEstadosReserva() {
        return estadoReservaRepository.findAll();
    }

    // Obtener un estado de reserva por su ID
    @Override
    public Optional<EstadoReserva> getEstadoReservaById(Integer id) {
        return estadoReservaRepository.findById(id);
    }

    // Actualizar un estado de reserva existente
    @Override
    public EstadoReserva updateEstadoReserva(Integer id, EstadoReserva estadoReservaDetails) {
        return estadoReservaRepository.findById(id).map(estadoReserva -> {
            estadoReserva.setNombre(estadoReservaDetails.getNombre());
            estadoReserva.setDescripcion(estadoReservaDetails.getDescripcion());
            return estadoReservaRepository.save(estadoReserva);
        }).orElseThrow(() -> new RuntimeException("Estado de reserva no encontrado con ID: " + id));
    }

    // Eliminar un estado de reserva por su ID
    @Override
    public void deleteEstadoReserva(Integer id) {
        if (estadoReservaRepository.existsById(id)) {
            estadoReservaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Estado de reserva no encontrado con ID: " + id);
        }
    }
}