package grupo1.labtic.services.entities.restaurant.comida;

import grupo1.labtic.services.entities.restaurant.Restaurant;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Cocina {

    @EmbeddedId
    private CocinaPK cocinaPK;

    public Cocina() {
        cocinaPK = new CocinaPK();
    }

    public Cocina(Restaurant rest, long id) {
        cocinaPK = new CocinaPK();
        cocinaPK.setCocina_Restaurant(rest);
        cocinaPK.setId(id);
    }

    public void setRestaurantCocina(Restaurant restaurante){
        this.cocinaPK.setCocina_Restaurant(restaurante);
    }
    public void setId( long id){
        this.cocinaPK.setId(id);
    }
    public Restaurant getRestaurantCocina(){
        return this.cocinaPK.getCocina_Restaurant();
    }
    public long getId( ){
       return this.cocinaPK.getId();
    }
    public CocinaPK getCocinaPK() {
        return cocinaPK;
    }

    public void setCocinaPK(CocinaPK cocinaPK) {
        this.cocinaPK = cocinaPK;
    }
}
class CocinaPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCocina_Restaurant(Restaurant cocina_Restaurant) {
        Cocina_Restaurant = cocina_Restaurant;
    }
}