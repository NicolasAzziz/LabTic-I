package grupo1.labtic.bdd;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Restaurant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsultasTest {

    RestaurantRepository restaurantRepository;

    @Autowired
    public ConsultasTest(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Iterable<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    @Test
    public void main() {

    }
}
