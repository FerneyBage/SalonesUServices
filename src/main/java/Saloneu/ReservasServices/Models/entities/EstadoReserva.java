package Saloneu.ReservasServices.Models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Estado_reservas")
public class EstadoReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 255)
    @Nationalized
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "idEstado")
    private Set<Reserva> reservas = new LinkedHashSet<>();

}