package Saloneu.ReservasServices.Models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Notificaciones")
public class Notificacione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva")
    private Reserva idReserva;

    @Size(max = 20)
    @NotNull
    @Column(name = "tipo_notificacion", nullable = false, length = 20)
    private String tipoNotificacion;

    @NotNull
    @Column(name = "fecha_envio", nullable = false)
    private Instant fechaEnvio;

    @ColumnDefault("0")
    @Column(name = "estado")
    private Boolean estado;

}