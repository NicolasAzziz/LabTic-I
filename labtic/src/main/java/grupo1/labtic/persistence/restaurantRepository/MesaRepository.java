package grupo1.labtic.persistence.restaurantRepository;

import grupo1.labtic.services.entities.restaurant.Mesa;
//import grupo1.labtic.services.entities.restaurant.MesaPK;
import grupo1.labtic.services.entities.restaurant.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MesaRepository extends CrudRepository<Mesa, Mesa> {

}
