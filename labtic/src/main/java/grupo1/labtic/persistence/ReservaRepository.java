package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, String> {

    Iterable<Reserva> getReservaByCliente_EmailAndEstado(String clienteEmail, String estado);

    Iterable<Reserva> getReservasByEstadoIs(String estado);

    Iterable<Reserva> getReservaByRestaurantAndEstado(Restaurant restaurant, String estado);

    Reserva getReservaById(long id);

    Iterable<Reserva> getReservasByCliente_Email(String clienteEmail);

    boolean existsByClienteAndRestaurantAndNroReferenciaAndEstado(Cliente cliente, Restaurant restaurant, int nroReserva, String estado);

    Reserva getById(long id);

}
