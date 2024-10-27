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
@Table(name = "Edificios")
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_edificio", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_edificio", nullable = false, length = 100)
    private String nombreEdificio;

    @OneToMany(mappedBy = "idEdificio")
    private Set<Salone> salones = new LinkedHashSet<>();

}