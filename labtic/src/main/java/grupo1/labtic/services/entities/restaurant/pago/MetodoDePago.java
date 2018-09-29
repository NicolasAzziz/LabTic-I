package grupo1.labtic.services.entities.restaurant.pago;

import grupo1.labtic.services.entities.restaurant.Restaurant;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class MetodoDePago {
    @EmbeddedId
    private MetodoDePagoPK id;

    public MetodoDePagoPK getId() {
        return id;
    }

    public void setId(MetodoDePagoPK id) {
        this.id = id;
    }
}

class MetodoDePagoPK implements Serializable {

    @ManyToOne
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

    public void setMetodoDePagoRestaurant(Restaurant metodoDePagoRestaurant) {
        MetodoDePagoRestaurant = metodoDePagoRestaurant;
    }
}