package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.restaurant.Restaurant;
import org.springframework.data.repository.CrudRepository;


public interface RestaurantRepository extends CrudRepository<Usuario, String> {
    Usuario findOneByLogin(String login);
    Restaurant findOneById(long id);
}
