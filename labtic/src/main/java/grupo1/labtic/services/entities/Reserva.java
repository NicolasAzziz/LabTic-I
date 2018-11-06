package grupo1.labtic.services.entities;

import grupo1.labtic.services.entities.restaurant.Mesa;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "RESERVA")
public class Reserva {
//
    @Id
    @GeneratedValue(generator = "reserva_id")
    @GenericGenerator(name = "reserva_id", strategy = "increment")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
//    @DateTimeFormat
//    @Column(name = "fechaYhora")
//    private Date fechaYhora;
//    @ElementCollection
//    @CollectionTable(name = "Mesa", joinColumns = @JoinColumn(name = "Restaurant"))
//    private Mesa mesa;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CLIENTE_ID")
//    private Cliente cliente;
//    @Column
//    private boolean aceptar;
//
//
//    public Reserva(Cliente cliente, Mesa mesa) {
//        aceptar=false;
//        this.cliente = cliente;
//        this.mesa = mesa;
//        fechaYhora = new Date();
//    }
//
//    public Reserva() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Date getFechaYhora() {
//        return fechaYhora;
//    }
//
//    public void setFechaYhora(Date fechaYhora) {
//        this.fechaYhora = fechaYhora;
//    }
//
//    public Mesa getMesa() {
//        return mesa;
//    }
//
//    public void setMesa(Mesa mesa) {
//        this.mesa = mesa;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Reserva reserva = (Reserva) o;
//        return id == reserva.id &&
//                Objects.equals(fechaYhora, reserva.fechaYhora) &&
//                Objects.equals(mesa, reserva.mesa) &&
//                Objects.equals(cliente, reserva.cliente);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, fechaYhora, mesa, cliente);
//    }
}

