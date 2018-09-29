package grupo1.labtic.services.entities.restaurant.pago;

import grupo1.labtic.services.entities.restaurant.Restaurant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class MetodoDePago {
    @EmbeddedId
    private MetodoDePagoPK metodoDePagoPK;

    public MetodoDePago() {
        metodoDePagoPK = new MetodoDePagoPK();
    }

    public MetodoDePago(Restaurant rest, long metodoDePagoPK) {
        this.metodoDePagoPK = new MetodoDePagoPK();
        this.metodoDePagoPK.setMetodoDePagoRestaurant(rest);
        this.metodoDePagoPK.setId(metodoDePagoPK);
    }

    public void setRestauranteMetodoDePago(Restaurant rest){
        metodoDePagoPK.setMetodoDePagoRestaurant(rest);
    }
    public void setId(long id){
        metodoDePagoPK.setId(id);
    }
    public Restaurant getRestauranteMetodoDePago(){
        return metodoDePagoPK.getMetodoDePagoRestaurant();
    }
    public long getId(){
        return  metodoDePagoPK.getId();
    }

    public MetodoDePagoPK getMetodoDePagoPK() {
        return metodoDePagoPK;
    }

    public void setMetodoDePagoPK(MetodoDePagoPK metodoDePagoPK) {
        this.metodoDePagoPK = metodoDePagoPK;
    }
}

class MetodoDePagoPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurantId")
    private Restaurant MetodoDePagoRestaurant;
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetodoDePagoPK that = (MetodoDePagoPK) o;
        return id == that.id &&
                Objects.equals(MetodoDePagoRestaurant, that.MetodoDePagoRestaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MetodoDePagoRestaurant, id);
    }

    public Restaurant getMetodoDePagoRestaurant() {
        return MetodoDePagoRestaurant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMetodoDePagoRestaurant(Restaurant metodoDePagoRestaurant) {
        MetodoDePagoRestaurant = metodoDePagoRestaurant;
    }
}