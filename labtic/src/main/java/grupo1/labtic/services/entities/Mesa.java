package grupo1.labtic.services.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MESAS")
public class Mesa {


    @Id
    //RESTAURANTE EN STRING PORQUE NO SABE QUE ES EL TYPO RESTAURANTE.JAVA a la hora ode crear la bd
    private String restaurante;

    @Column(name = "numeroReferencia", unique =  true)
    @NotNull
    private int numeroReferencia;
    @Column(name = "cantidadDeLugares")
    @NotNull
    private int cantLugares;

    public Mesa(int numeroReferencia, int cantLugares, String restaurante) {
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
    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }
}

