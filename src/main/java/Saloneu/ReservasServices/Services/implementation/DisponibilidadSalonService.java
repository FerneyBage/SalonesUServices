package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.DisponibilidadSalon;
import Saloneu.ReservasServices.Models.entities.Reserva;
import Saloneu.ReservasServices.Repositories.IDisponibilidadSaloneRepository;
import Saloneu.ReservasServices.Repositories.IReservaRepository;
import Saloneu.ReservasServices.Repositories.ISaloneRepository;
import Saloneu.ReservasServices.Services.contract.IDisponibilidadSalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisponibilidadSalonService implements IDisponibilidadSalonService {

    private final IDisponibilidadSaloneRepository DisponibilidadSalonRepository;
    private final ISaloneRepository salonesRepository;
    private final IReservaRepository reservaRepository;

    // Inyecta los valores de las variables de entorno
    @Value("${reserva.estado.pending}")
    private Integer estadoPending;

    @Value("${reserva.estado.enCurso}")
    private Integer estadoEnCurso;

    @Autowired
    public DisponibilidadSalonService(IDisponibilidadSaloneRepository DisponibilidadSalonRepository,
                                      ISaloneRepository salonesRepository,
                                      IReservaRepository reservaRepository) {
        this.DisponibilidadSalonRepository = DisponibilidadSalonRepository;
        this.salonesRepository = salonesRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public DisponibilidadSalon createDisponibilidad(DisponibilidadSalon DisponibilidadSalon) {
        // Verificar que el salón existe
        Integer salonId = DisponibilidadSalon.getIdSalon().getId();
        if (!salonesRepository.existsById(salonId)) {
            throw new RuntimeException("El salón con ID " + salonId + " no existe.");
        }
        return DisponibilidadSalonRepository.save(DisponibilidadSalon);
    }

    @Override
    public List<DisponibilidadSalon> getAllDisponibilidades() {
        return DisponibilidadSalonRepository.findAll();
    }

    @Override
    public Optional<DisponibilidadSalon> getDisponibilidadById(Integer id) {
        return DisponibilidadSalonRepository.findById(id);
    }

    @Override
    public DisponibilidadSalon updateDisponibilidad(Integer id, DisponibilidadSalon DisponibilidadSalonDetails) {
        // Verificar que el salón existe
        Integer salonId = DisponibilidadSalonDetails.getIdSalon().getId();
        if (!salonesRepository.existsById(salonId)) {
            throw new RuntimeException("El salón con ID " + salonId + " no existe.");
        }

        return DisponibilidadSalonRepository.findById(id).map(DisponibilidadSalon -> {
            DisponibilidadSalon.setHoraInicio(DisponibilidadSalonDetails.getHoraInicio());
            DisponibilidadSalon.setHoraFin(DisponibilidadSalonDetails.getHoraFin());
            DisponibilidadSalon.setVisibilidad(DisponibilidadSalonDetails.getVisibilidad());
            DisponibilidadSalon.setIdSalon(DisponibilidadSalonDetails.getIdSalon());
            return DisponibilidadSalonRepository.save(DisponibilidadSalon);
        }).orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada con ID: " + id));
    }

    @Override
    public void deleteDisponibilidad(Integer id) {
        DisponibilidadSalonRepository.deleteById(id);
    }

    public List<DisponibilidadSalon> disponiblesParaReserva(Integer salonId, LocalDate fecha) {
        // Verificar que el salón existe
        if (!salonesRepository.existsById(salonId)) {
            throw new RuntimeException("El salón con ID " + salonId + " no existe.");
        }

        // Definir el rango del día (de 00:00 a 23:59)
        LocalDateTime inicioDelDia = fecha.atTime(LocalTime.MIN); // 00:00
        LocalDateTime finDelDia = fecha.atTime(LocalTime.MAX);    // 23:59:59.999999999

        // Obtener todas las reservas del salón en estado "pending" o "en curso" que caen dentro del intervalo de la fecha ingresada
        List<Reserva> reservasDelDia = reservaRepository.findAll().stream()
                .filter(reserva -> reserva.getIdSalon().getId().equals(salonId)
                        && !reserva.getFecha().isBefore(inicioDelDia)
                        && !reserva.getFecha().isAfter(finDelDia)
                        && (reserva.getIdEstado().getId().equals(estadoPending) || reserva.getIdEstado().getId().equals(estadoEnCurso)))
                .collect(Collectors.toList());

        // Obtener todas las disponibilidades del salón y aplicar los filtros adicionales
        List<DisponibilidadSalon> disponibilidades = DisponibilidadSalonRepository.findAll().stream()
                .filter(disponibilidad -> disponibilidad.getIdSalon().getId().equals(salonId)
                        && (fecha.isAfter(LocalDate.now()) || disponibilidad.getHoraInicio().isAfter(LocalTime.now()))) // Filtro por hora actual si es el mismo día
                .filter(disponibilidad -> reservasDelDia.stream().noneMatch(reserva ->
                        reserva.getHoraInicio().isBefore(disponibilidad.getHoraFin()) &&
                                reserva.getHoraFin().isAfter(disponibilidad.getHoraInicio())))
                .collect(Collectors.toList());

        return disponibilidades;
    }
}
