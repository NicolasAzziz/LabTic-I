package grupo1.labtic.services.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MESAS")
public class Mesa {
    @Id
    private Restaurant restaurante;
    @Id
    @NotNull
    private int numeroReferencia;
    @Column(name = "cantidadDeLugares")
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
}
