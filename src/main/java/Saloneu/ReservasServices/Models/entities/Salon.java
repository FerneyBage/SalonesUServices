package Saloneu.ReservasServices.Models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Salones")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salon", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotNull
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Size(max = 255)
    @Column(name = "ubicacion")
    private String ubicacion;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_salon")
    private TiposSalon TipoSalon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_edificio")
    private Edificio Edificio;

    @OneToMany(mappedBy = "idSalon")
    private Set<DisponibilidadSalon> disponibilidadSalones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idSalon")
    private Set<Reserva> reservas = new LinkedHashSet<>();

}