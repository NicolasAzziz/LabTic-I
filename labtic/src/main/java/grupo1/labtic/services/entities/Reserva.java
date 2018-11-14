package grupo1.labtic.services.entities;

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

    @DateTimeFormat
    @Column(name = "fechaYhora")
    private Date fechaYhora;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    private int nroReferencia;

    @Column
    private String estado;

    public Reserva() {
    }

    public Reserva(Cliente cliente, Restaurant restaurant, int nroReferencia) {
        this.cliente = cliente;
        this.restaurant = restaurant;
        this.nroReferencia = nroReferencia;
        fechaYhora = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return nroReferencia == reserva.nroReferencia &&
                Objects.equals(fechaYhora, reserva.fechaYhora) &&
                Objects.equals(cliente, reserva.cliente) &&
                Objects.equals(restaurant, reserva.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaYhora, cliente, restaurant, nroReferencia);
    }

    public Date getFechaYhora() {
        return fechaYhora;
    }

    public void setFechaYhora(Date fechaYhora) {
        this.fechaYhora = fechaYhora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getNroReferencia() {
        return nroReferencia;
    }

    public void setNroReferencia(int nroReferencia) {
        this.nroReferencia = nroReferencia;
    }
}

