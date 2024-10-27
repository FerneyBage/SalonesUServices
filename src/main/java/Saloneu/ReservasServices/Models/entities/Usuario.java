package Saloneu.ReservasServices.Models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"contraseña\"", nullable = false)
    private String contraseña;

    @ColumnDefault("1")
    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Role idRol;

    @OneToMany(mappedBy = "idUsuario")
    private Set<Notificacione> notificaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Reserva> reservas = new LinkedHashSet<>();

}