package grupo1.labtic.persistence.restaurantRepository;

import grupo1.labtic.services.entities.restaurant.comida.Cocina;
import grupo1.labtic.services.entities.restaurant.comida.Comida;
import org.springframework.data.repository.CrudRepository;

public interface ComidaRepository extends CrudRepository<Comida, String> {
    public Comida getComidaById(long id);
    
}
