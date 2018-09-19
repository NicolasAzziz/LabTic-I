package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Restaurant findOneByNombre(String nombre);
}
