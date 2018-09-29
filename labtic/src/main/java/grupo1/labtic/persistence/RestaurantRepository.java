package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.restaurant.Restaurant;
import org.springframework.data.repository.CrudRepository;


public interface RestaurantRepository extends CrudRepository<Restaurant, String> {
    Restaurant findOneByLogin(String login);
    Restaurant getRestaurantById(long id);
    Restaurant findOneById(long id);
}
