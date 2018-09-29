package grupo1.labtic.services.entities.restaurant.comida;

import grupo1.labtic.services.entities.restaurant.Restaurant;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Cocina {

    @EmbeddedId
    private CocinaPK id;

    public CocinaPK getId() {
        return id;
    }

    public void setId(CocinaPK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocina cocina = (Cocina) o;
        return Objects.equals(id, cocina.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
class CocinaPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant Cocina_Restaurant;
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocinaPK cocinaPK = (CocinaPK) o;
        return id == cocinaPK.id &&
                Objects.equals(Cocina_Restaurant, cocinaPK.Cocina_Restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Cocina_Restaurant, id);
    }

    public Restaurant getCocina_Restaurant() {
        return Cocina_Restaurant;
    }

    public void setCocina_Restaurant(Restaurant cocina_Restaurant) {
        Cocina_Restaurant = cocina_Restaurant;
    }
}