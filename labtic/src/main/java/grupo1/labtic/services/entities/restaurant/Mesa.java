package grupo1.labtic.services.entities.restaurant;

import grupo1.labtic.services.entities.Restaurant;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class Mesa {

    @Column(name = "nroReferencia")
    private Integer nroReferencia;

    @Column(name = "cantLugares")
    private Integer cantLugares;

    @Column(name = "libre")
    private boolean mesaLibre;

    public Mesa() {
    }

    public Mesa(Restaurant rest, Integer numeroReferencia, Integer cantLugares) {
        nroReferencia = numeroReferencia;
        this.cantLugares = cantLugares;
        mesaLibre = true;

        //cantLugares= 1;
    }


    public Mesa(int nroReferencia, int cantLugares) {
        setNroReferencia(nroReferencia);
        setCantLugares(cantLugares);
        mesaLibre = true;
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
        return mesaLibre == mesa.mesaLibre &&
                Objects.equals(nroReferencia, mesa.nroReferencia) &&
                Objects.equals(cantLugares, mesa.cantLugares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroReferencia, cantLugares, mesaLibre);
    }

    public Integer getNroReferencia() {
        return nroReferencia;
    }

    public void setNroReferencia(Integer nroReferencia) {
        this.nroReferencia = nroReferencia;
    }


    public Integer getCantLugares() {
        return cantLugares;
    }

    public void setCantLugares(Integer cantLugares) {
        this.cantLugares = cantLugares;
    }

    @Override
    public String toString() {
        return "No. de mesa: " + getNroReferencia() + ".  No. lugares: " + getCantLugares() + ".";
    }

    public String getEstado() {
        String estado = "";
        if(isMesaLibre()){
            estado = "Libre";
        } else if(isMesaLibre() == false){
            estado = "Ocupada";
        }
        return estado;
    }
}

//
//class MesaPK implements Serializable {
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "restaurantId")
//    private Restaurant MesaRestaurant;
//    private Integer nroReferencia;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MesaPK mesaPK = (MesaPK) o;
//        return MesaRestaurant == mesaPK.MesaRestaurant &&
//                nroReferencia == mesaPK.nroReferencia;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(MesaRestaurant, nroReferencia);
//    }
//
//    public Restaurant getMesaRestaurant() {
//        return MesaRestaurant;
//    }
//
//    public void setMesaRestaurant(Restaurant mesaRestaurant) {
//        this.MesaRestaurant = mesaRestaurant;
//    }
//
//    public Integer getNroReferencia() {
//        return nroReferencia;
//    }
//
//    public void setNroReferencia(int nroReferencia) {
//        this.nroReferencia = nroReferencia;
//    }
//}