package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.DisponibilidadSalon;
import Saloneu.ReservasServices.Repositories.IDisponibilidadSaloneRepository;
import Saloneu.ReservasServices.Repositories.ISaloneRepository;
import Saloneu.ReservasServices.Services.contract.IDisponibilidadSalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadSalonService implements IDisponibilidadSalonService {

    private final IDisponibilidadSaloneRepository DisponibilidadSalonRepository;
    private final ISaloneRepository salonesRepository;

    @Autowired
    public DisponibilidadSalonService(IDisponibilidadSaloneRepository DisponibilidadSalonRepository,
                                            ISaloneRepository salonesRepository) {
        this.DisponibilidadSalonRepository = DisponibilidadSalonRepository;
        this.salonesRepository = salonesRepository;
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
}
