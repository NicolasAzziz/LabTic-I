package grupo1.labtic.persistence.restaurantRepository;

import grupo1.labtic.services.entities.restaurant.pago.MetodoDePago;
import org.springframework.data.repository.CrudRepository;

public interface MetodoDePagoRepository extends CrudRepository<MetodoDePago, MetodoDePago> {
}
