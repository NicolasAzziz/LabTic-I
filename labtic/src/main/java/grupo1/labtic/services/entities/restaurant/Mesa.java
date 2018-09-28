package grupo1.labtic.services.entities.restaurant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "MESA")
public class Mesa {

    @EmbeddedId
    private MesaPK mesaPK;

    @Column(name = "cantidadDeLugares")
    @NotNull
    private int cantLugares;

    @Column(name = "libre")
    private boolean mesaLibre;

    public Mesa(int numeroReferencia, int cantLugares) {
        this.mesaPK.setNroReferencia( numeroReferencia );
        this.cantLugares = cantLugares;
        mesaLibre = true;
    }

    public int getNumeroReferencia() {
        return this.mesaPK.getNroReferencia();
    }

    public int getCantLugares() {
        return cantLugares;
    }

    public void setNumeroReferencia(int numeroReferencia) {
        this.mesaPK.setNroReferencia(numeroReferencia);
    }

    public boolean getEstaLibre (){
        return mesaLibre;
    }

    public void setLibre(){
        mesaLibre = true;
    }
    public void setOcupada(){
        mesaLibre = false;
    }

    public void setCantLugares(int cantLugares) {
        this.cantLugares = cantLugares;
    }

    public long getRestaurantId() {
        return this.mesaPK.getMesaRestaurant().getId();
    }
}


class MesaPK implements Serializable{
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant MesaRestaurant;
    private int nroReferencia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesaPK mesaPK = (MesaPK) o;
        return MesaRestaurant == mesaPK.MesaRestaurant &&
                nroReferencia == mesaPK.nroReferencia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(MesaRestaurant, nroReferencia);
    }

    public Restaurant getMesaRestaurant() {
        return MesaRestaurant;
    }

    public void setMesaRestaurant(Restaurant mesaRestaurant) {
        this.MesaRestaurant = mesaRestaurant;
    }

    public int getNroReferencia() {
        return nroReferencia;
    }

    public void setNroReferencia(int nroReferencia) {
        this.nroReferencia = nroReferencia;
    }
}