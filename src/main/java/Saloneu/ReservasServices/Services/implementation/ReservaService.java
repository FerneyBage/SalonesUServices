package Saloneu.ReservasServices.Services.implementation;

import Saloneu.ReservasServices.Repositories.IReservaRepository;

public class ReservaService {
    private final IReservaRepository repositorio;

    public ReservaService(IReservaRepository repositorio) {
        this.repositorio = repositorio;
    }
}
