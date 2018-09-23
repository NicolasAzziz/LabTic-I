package grupo1.labtic.services.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MESAS")
public class Mesa {




    @Id
    @Column(name = "numeroReferencia")
    private int numeroReferencia;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario restaurante;
    @Column(name = "cantidadDeLugares")
    @NotNull
    private int cantLugares;

    public Mesa(int numeroReferencia, int cantLugares, Restaurant restaurante) {
        this.restaurante = restaurante;
        this.numeroReferencia = numeroReferencia;
        this.cantLugares = cantLugares;
    }

    public Mesa() {
    }

    public int getNumeroReferencia() {
        return numeroReferencia;
    }

    public int getCantLugares() {
        return cantLugares;
    }

    public void setNumeroReferencia(int numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public void setCantLugares(int cantLugares) {
        this.cantLugares = cantLugares;
    }
    public Usuario getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurant restaurante) {
        this.restaurante = restaurante;
    }
}

