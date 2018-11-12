package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.restaurant.Barrio;
import org.springframework.data.repository.CrudRepository;


public interface BarrioRepository extends CrudRepository<Barrio, String> {
    boolean existsByBarrio(String barrio);
}
