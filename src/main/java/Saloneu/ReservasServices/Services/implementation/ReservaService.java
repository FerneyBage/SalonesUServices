package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Repositories.IReservaRepository;

import Saloneu.ReservasServices.Models.entities.*;
import Saloneu.ReservasServices.Repositories.*;
import Saloneu.ReservasServices.Services.contract.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class ReservaService implements IReservaService {

    private final IReservaRepository reservaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ISaloneRepository salonRepository;
    private final IDisponibilidadSaloneRepository disponibilidadSalonRepository;
    private final IEstadoReservaRepository estadoReservaRepository;

    @Value("${id_estado_pending}")
    private Integer idEstadoPending;

    @Autowired
    public ReservaService(IReservaRepository reservaRepository,
                          IUsuarioRepository usuarioRepository,
                          ISaloneRepository salonRepository,
                          IDisponibilidadSaloneRepository disponibilidadSalonRepository,
                          IEstadoReservaRepository estadoReservaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.salonRepository = salonRepository;
        this.disponibilidadSalonRepository = disponibilidadSalonRepository;
        this.estadoReservaRepository = estadoReservaRepository;
    }

    @Override
    public Reserva crearReserva(Reserva reserva) {
        // Verificar que el usuario exista
        if (!usuarioRepository.existsById(reserva.getIdUsuario().getId())) {
            throw new RuntimeException("El usuario con ID " + reserva.getIdUsuario().getId() + " no existe.");
        }

        // Verificar que el salón exista
        if (!salonRepository.existsById(reserva.getIdSalon().getId())) {
            throw new RuntimeException("El salón con ID " + reserva.getIdSalon().getId() + " no existe.");
        }

        // Verificar que la disponibilidad exista
        DisponibilidadSalon disponibilidad = disponibilidadSalonRepository.findById(reserva.getIdDisponibilidad().getId())
                .orElseThrow(() -> new RuntimeException("La disponibilidad con ID " + reserva.getIdDisponibilidad().getId() + " no existe."));

        // Definir los límites de fecha y hora para la validación
        LocalDate fechaNuevaReserva = reserva.getFecha().toLocalDate();
        LocalDateTime inicioDelDia = fechaNuevaReserva.atStartOfDay(); // 00:00 del día de la reserva
        LocalDateTime finDelDia = fechaNuevaReserva.atTime(23, 59); // 23:59 del día de la reserva

        // Verificar si alguna reserva existente en estado "pending" cae dentro de los límites del día completo
        boolean reservaPendienteExistente = reservaRepository.findAll().stream()
                .anyMatch(r -> r.getFecha().isAfter(inicioDelDia) && r.getFecha().isBefore(finDelDia) &&
                        r.getIdEstado().getId().equals(idEstadoPending) && r.getIdDisponibilidad().getId().equals(reserva.getIdDisponibilidad().getId()));

        if (reservaPendienteExistente) {
            throw new RuntimeException("Ya existe una reserva en estado 'pending' para el salón en el mismo día.");
        }

        // Verificar si el usuario tiene más de dos reservas en estado "pending"
        long reservasPendientesUsuario = reservaRepository.findAll().stream()
                .filter(r -> r.getIdUsuario().getId().equals(reserva.getIdUsuario().getId()) &&
                        r.getIdEstado().getId().equals(idEstadoPending))
                .count();

        if (reservasPendientesUsuario >= 2) {
            throw new RuntimeException("El usuario con ID " + reserva.getIdUsuario().getId() + " ya tiene dos reservas en estado 'pending'.");
        }

        // Asignar valores a los campos de la nueva reserva
        reserva.setFechaCreacion(Instant.now());
        reserva.setIdEstado(estadoReservaRepository.findById(idEstadoPending)
                .orElseThrow(() -> new RuntimeException("El estado con ID " + idEstadoPending + " no existe.")));

        // Ajustar la hora de inicio y fin para la nueva reserva
        reserva.setHoraInicio(disponibilidad.getHoraInicio().minusMinutes(15)); // 15 minutos antes de la hora de inicio de la disponibilidad
        reserva.setHoraFin(disponibilidad.getHoraFin());

        // Guardar y retornar la reserva
        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> getReservasByUsuarioId(Integer usuarioId) {
        return reservaRepository.findByIdUsuarioId(usuarioId);
    }

    public Reserva actualizarEstadoReserva(Integer reservaId, Integer estadoId) {
        // Verificar si el estado existe
        Optional<EstadoReserva> estadoReservaOptional = estadoReservaRepository.findById(estadoId);
        if (!estadoReservaOptional.isPresent()) {
            throw new IllegalArgumentException("El estado especificado no existe");
        }

        // Verificar si la reserva existe
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("La reserva especificada no existe"));

        // Actualizar el estado de la reserva
        reserva.setIdEstado(estadoReservaOptional.get());
        return reservaRepository.save(reserva);
    }
}
