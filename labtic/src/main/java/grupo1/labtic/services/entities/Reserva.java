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

    @DateTimeFormat
    @Column(name = "fechaYhora")
    private Date fechaYhora;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column
    private int nroReferencia;

    @Column
    private String estado;

    @Column
    private Long importe = 0L;

    public Reserva(){}

    public Reserva(Cliente cliente, Restaurant restaurant, int nroReferencia) {
        this.cliente = cliente;
        this.restaurant = restaurant;
        this.nroReferencia = nroReferencia;
        fechaYhora = new Date();
        setEstadoSolicitado();
    }

    public String getNombreRestaurant(){
        return restaurant.getNombreRestaurant();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return nroReferencia == reserva.nroReferencia &&
                Objects.equals(fechaYhora, reserva.fechaYhora) &&
                Objects.equals(cliente.getEmail(), reserva.cliente.getEmail()) &&
                Objects.equals(restaurant.getEmail(), reserva.restaurant.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaYhora, cliente.getEmail(), restaurant.getEmail(), nroReferencia);
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

    public Mesa getMesa(){
        Mesa eMesa = null;
        for (Mesa mesa:restaurant.getMesas()
             ) {
            if(nroReferencia == mesa.getNroReferencia()){
                eMesa = mesa;
            }
        }
        return eMesa;
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

    public Integer getCantLugares(){
        return getMesa().getCantLugares();
    }

    public void setEstadoSolicitado(){
        estado = "Solicitado";
    }

    public String getNombreCliente(){
        return cliente.getNombre();
    }

    public void setEstadoAceptado(){
        estado = "Aceptado";
        restaurant.getMesa(nroReferencia).setMesaLibre(false);
    }

    public void setEstadoRechazado(){
        estado = "Rechazado";
    }

    public void setEstadoFinalizado(){
        estado = "Finalizado";
        restaurant.getMesa(nroReferencia).setMesaLibre(true);
    }

    public void setEstadoCancelado(){
        estado = "Cancelado";
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

    public Long getImporte() {
        return importe;
    }

    public void setImporte(Long importe) {
        this.importe = importe;
    }
}

