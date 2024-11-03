package Saloneu.ReservasServices.Services.contract;

import Saloneu.ReservasServices.Models.entities.DisponibilidadSalon;

import java.util.List;
import java.util.Optional;

public interface IDisponibilidadSalonService {

    DisponibilidadSalon createDisponibilidad(DisponibilidadSalon DisponibilidadSalon);

    List<DisponibilidadSalon> getAllDisponibilidades();

    Optional<DisponibilidadSalon> getDisponibilidadById(Integer id);

    DisponibilidadSalon updateDisponibilidad(Integer id, DisponibilidadSalon DisponibilidadSalonDetails);

    void deleteDisponibilidad(Integer id);
}
