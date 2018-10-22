package grupo1.labtic.services.entities.restaurant;

import grupo1.labtic.services.entities.Restaurant;

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
    private Integer cantLugares;

    @Column(name = "libre")
    private boolean mesaLibre;

    public Mesa() {
        mesaPK= new MesaPK();
    }

    public Mesa(Restaurant rest, Integer numeroReferencia, Integer cantLugares) {
        mesaPK = new MesaPK();
        this.mesaPK.setMesaRestaurant(rest);
        this.mesaPK.setNroReferencia(numeroReferencia);
        this.cantLugares = cantLugares;
        mesaLibre = true;

        //cantLugares= 1;
    }

    public Mesa(MesaPK mesaPK, @NotNull int cantLugares) {
        this.mesaPK = mesaPK;
        this.cantLugares = cantLugares;
        mesaLibre = true;
    }

    public Mesa(int nroReferencia, int cantLugares){
        mesaPK = new MesaPK();
        setNumeroReferencia(nroReferencia);
        setCantLugares(cantLugares);
        mesaLibre = true;
    }

    public MesaPK getMesaPK() {
        return mesaPK;
    }

    public void setMesaPK(MesaPK mesaPK) {
        this.mesaPK = mesaPK;
    }

    public boolean isMesaLibre() {
        return mesaLibre;
    }

    public void setMesaLibre(boolean mesaLibre) {
        this.mesaLibre = mesaLibre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesa mesa = (Mesa) o;
        return cantLugares == mesa.cantLugares &&
                mesaLibre == mesa.mesaLibre &&
                Objects.equals(mesaPK, mesa.mesaPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mesaPK, cantLugares, mesaLibre);
    }

    public Integer getNumeroReferencia() {
        return this.mesaPK.getNroReferencia();
    }

    public void setNumeroReferencia(int numeroReferencia) {
        this.mesaPK.setNroReferencia(numeroReferencia);
    }

    public Integer getCantLugares() {
        return cantLugares;
    }

    public void setCantLugares(int cantLugares) {
        this.cantLugares = cantLugares;
    }

    public boolean getEstaLibre() {
        return mesaLibre;
    }

    public void setLibre() {
        mesaLibre = true;
    }

    public void setOcupada() {
        mesaLibre = false;
    }

    public Restaurant getRestaurant() {
        return this.mesaPK.getMesaRestaurant();
    }

    public void setRestaurant(Restaurant res) {
        this.mesaPK.setMesaRestaurant(res);
    }

    @Override
    public String toString(){
        return "No. de mesa: " + getNumeroReferencia()+".  No. lugares: "+getCantLugares()+".";
    }
}


class MesaPK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurantId")
    private Restaurant MesaRestaurant;
    private Integer nroReferencia;

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

    public Integer getNroReferencia() {
        return nroReferencia;
    }

    public void setNroReferencia(int nroReferencia) {
        this.nroReferencia = nroReferencia;
    }
}