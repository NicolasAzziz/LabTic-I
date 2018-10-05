package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;


public interface RestaurantRepository extends CrudRepository<Restaurant, String> {
    Usuario findOneByLogin(String login);
    Usuario getOneByLogin(String login);
    Restaurant getRestaurantById(long id);
    Restaurant getRestaurantByLogin(String login);
    Restaurant findOneById(long id);
}
