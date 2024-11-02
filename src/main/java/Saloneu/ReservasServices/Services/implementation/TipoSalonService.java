package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.TiposSalon;
import Saloneu.ReservasServices.Repositories.ITiposSalonRepository;
import Saloneu.ReservasServices.Services.contract.ITipoSalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoSalonService implements ITipoSalonService {

    private final ITiposSalonRepository TiposSalonRepository;

    @Autowired
    public TipoSalonService(ITiposSalonRepository TiposSalonRepository) {
        this.TiposSalonRepository = TiposSalonRepository;
    }

    // Método para crear un nuevo tipo de salón
    public TiposSalon crearTiposSalon(TiposSalon TiposSalon) {
        return TiposSalonRepository.save(TiposSalon);
    }

    // Método para obtener un tipo de salón por su ID
    public Optional<TiposSalon> obtenerTiposSalonPorId(Integer id) {
        return TiposSalonRepository.findById(id);
    }

    // Método para obtener todos los tipos de salón
    public List<TiposSalon> obtenerTodosLosTiposSalon() {
        return TiposSalonRepository.findAll();
    }

    // Método para actualizar un tipo de salón existente
    @Transactional
    public TiposSalon actualizarTiposSalon(Integer id, TiposSalon detallesTiposSalon) {
        return TiposSalonRepository.findById(id).map(TiposSalon -> {
            TiposSalon.setNombreTipo(detallesTiposSalon.getNombreTipo());
            // Agrega aquí cualquier otro campo que necesites actualizar
            return TiposSalonRepository.save(TiposSalon);
        }).orElseThrow(() -> new RuntimeException("Tipo de Salón no encontrado con id " + id));
    }

    // Método para eliminar un tipo de salón por su ID
    public void eliminarTiposSalon(Integer id) {
        TiposSalonRepository.deleteById(id);
    }
}

