package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.Salon;
import Saloneu.ReservasServices.Repositories.IEdificioRepository;
import Saloneu.ReservasServices.Repositories.ISaloneRepository;
import Saloneu.ReservasServices.Repositories.ITiposSalonRepository;
import Saloneu.ReservasServices.Services.contract.ISalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalonService implements ISalonService {

    private final ISaloneRepository salonesRepository;
    private final IEdificioRepository edificioRepository;
    private final ITiposSalonRepository tiposSalonRepository;

    @Autowired
    public SalonService(ISaloneRepository salonesRepository, IEdificioRepository edificioRepository, ITiposSalonRepository tiposSalonRepository) {
        this.salonesRepository = salonesRepository;
        this.edificioRepository = edificioRepository;
        this.tiposSalonRepository = tiposSalonRepository;
    }

    @Override
    public Salon crearSalon(Salon salon) {
        // Verifica que el edificio exista
        if (!edificioRepository.existsById(salon.getEdificio().getId())) {
            throw new RuntimeException("El edificio con id " + salon.getEdificio().getId() + " no existe.");
        }

        // Verifica que el tipo de salón exista
        if (!tiposSalonRepository.existsById(salon.getTipoSalon().getId())) {
            throw new RuntimeException("El tipo de salón con id " + salon.getTipoSalon().getId() + " no existe.");
        }

        return salonesRepository.save(salon);
    }

    @Override
    public Optional<Salon> obtenerSalonPorId(Integer id) {
        return salonesRepository.findById(id);
    }

    @Override
    public List<Salon> obtenerTodosLosSalones() {
        return salonesRepository.findAll();
    }

    @Override
    @Transactional
    public Salon actualizarSalon(Integer id, Salon detallesSalon) {
        // Verifica que el edificio exista
        if (!edificioRepository.existsById(detallesSalon.getEdificio().getId())) {
            throw new RuntimeException("El edificio con id " + detallesSalon.getEdificio().getId() + " no existe.");
        }

        // Verifica que el tipo de salón exista
        if (!tiposSalonRepository.existsById(detallesSalon.getTipoSalon().getId())) {
            throw new RuntimeException("El tipo de salón con id " + detallesSalon.getTipoSalon().getId() + " no existe.");
        }

        return salonesRepository.findById(id).map(salon -> {
            salon.setNombre(detallesSalon.getNombre());
            salon.setCapacidad(detallesSalon.getCapacidad());
            salon.setUbicacion(detallesSalon.getUbicacion());
            salon.setDescripcion(detallesSalon.getDescripcion());
            salon.setTipoSalon(detallesSalon.getTipoSalon());
            salon.setEdificio(detallesSalon.getEdificio());
            return salonesRepository.save(salon);
        }).orElseThrow(() -> new RuntimeException("Salón no encontrado con id " + id));
    }

    @Override
    public void eliminarSalon(Integer id) {
        salonesRepository.deleteById(id);
    }
}