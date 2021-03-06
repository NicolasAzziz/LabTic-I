package grupo1.labtic;

import grupo1.labtic.persistence.ClienteRepository;
import grupo1.labtic.persistence.ReservaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.Mesa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class ReservasTest {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void agregarReserva() {
        Restaurant restaurant = new Restaurant("restaurant@reserva", "1234", 12345L, "direccion", "08:00", "23:00", "Malvin", "12345123", "Desc");
        Mesa mesa = new Mesa();
        mesa.setCantLugares(4);
        mesa.setNroReferencia(1);
        restaurant.setMesa(mesa);
        Restaurant r = restaurantRepository.getOneByEmail(restaurant.getEmail());
        if (r == null) {
            restaurantRepository.save(restaurant);
        }
        Cliente cliente = new Cliente("Federico Abdo Sosa", "fede@abdo", "43331539");

        Cliente c = clienteRepository.findByEmail(cliente.getEmail());

        if (c == null) {
            clienteRepository.save(cliente);
        }

        Reserva reserva = new Reserva(c, r, 1);

        reservaRepository.save(reserva);

        Iterable<Reserva> reservas = reservaRepository.findAll();

        reservas.forEach((reser -> System.out.println(reser.getCliente().getEmail() + reser.getRestaurant().getEmail())));
    }

}
