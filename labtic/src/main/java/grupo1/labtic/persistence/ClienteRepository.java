package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,String> {
    Cliente findByEmail(String email);
}
