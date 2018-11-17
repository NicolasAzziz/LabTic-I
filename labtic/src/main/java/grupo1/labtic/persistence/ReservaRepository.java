package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva, String> {

    Iterable<Reserva> getReservaByCliente_EmailAndEstado(String clienteEmail, String estado);

    Iterable<Reserva> getReservasByEstadoIs(String estado);

    Reserva getReservaById(long id);

    Iterable<Reserva> getReservasByCliente_Email(String clienteEmail);

    boolean existsByClienteAndRestaurantAndNroReferencia(Cliente cliente, Restaurant restaurant, int nroReserva);

    Reserva getById(long id);

    Iterable<Reserva> getReservasByEstadoIsAndRestaurant(String estado , Restaurant resto);


}
