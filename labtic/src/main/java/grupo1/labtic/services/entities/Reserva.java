package grupo1.labtic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "RESERVA")
public class Reserva {

    @Id
    @GeneratedValue(generator = "reserva_id")
    @GenericGenerator(name = "reserva_id", strategy = "increment")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @Column(name = "hora")
    private String hora;
    @Column(name = "fecha")
    private Date fecha;
    /*@ManyToOne
    @Column(name = "reservante_id")
    private long id_reservante;

    No se si esta bien esta relacion con el id del cliente que realiza la reserva
*/
    public Reserva(String hora, Date fecha) {
        this.hora = hora;
        this.fecha = fecha;
    }

    public Reserva() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hora, fecha);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

