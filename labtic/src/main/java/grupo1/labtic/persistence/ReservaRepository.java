package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, String> {
}
