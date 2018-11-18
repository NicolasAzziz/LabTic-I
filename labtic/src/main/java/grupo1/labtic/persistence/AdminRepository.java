package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, String> {

    Admin getByEmail(String email);

    boolean existsByEmail(String email);


}
