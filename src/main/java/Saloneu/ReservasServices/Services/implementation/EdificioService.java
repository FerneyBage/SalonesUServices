package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Models.entities.Edificio;
import Saloneu.ReservasServices.Repositories.IEdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EdificioService implements Saloneu.ReservasServices.Services.contract.IEdificioService {

    @Autowired
    private IEdificioRepository EdificioRepository;

    // Método para crear un edificio
    public Edificio crearEdificio(Edificio edificio) {
        return EdificioRepository.save(edificio);
    }

    // Método para obtener un edificio por su ID
    public Optional<Edificio> obtenerEdificioPorId(Integer id) {
        return EdificioRepository.findById(id);
    }

    // Método para obtener todos los Edificio
    public List<Edificio> obtenerTodosLosEdificio() {
        return EdificioRepository.findAll();
    }

    // Método para actualizar un edificio existente
    @Transactional
    public Edificio actualizarEdificio(Integer id, Edificio detallesEdificio) {
        Optional<Edificio> optionalEdificio = EdificioRepository.findById(id);
        if (optionalEdificio.isPresent()) {
            Edificio edificio = optionalEdificio.get();
            edificio.setNombreEdificio(detallesEdificio.getNombreEdificio());
            return EdificioRepository.save(edificio);
        } else {
            throw new RuntimeException("Edificio no encontrado con id " + id);
        }
    }

    // Método para eliminar un edificio por su ID
    public void eliminarEdificio(Integer id) {
        EdificioRepository.deleteById(id);
    }
}
