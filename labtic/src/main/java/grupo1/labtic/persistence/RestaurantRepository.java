package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface RestaurantRepository extends CrudRepository<Usuario, String> {

    Usuario findOneByLogin(String login);
}
