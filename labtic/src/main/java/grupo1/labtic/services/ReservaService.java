package grupo1.labtic.services;

import grupo1.labtic.persistence.ReservaRepository;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private RestaurantService restaurantService;


    public List<Reserva> reservasPendientes(){
        return (List)reservaRepository.getReservasByEstadoIs("Solicitado");
    }

    public void solicitarReserva(Cliente c, Restaurant r, int nroMesa){
        reservaRepository.save(new Reserva(c,r,nroMesa));
    }

    public void save(Reserva reserva){

        reservaRepository.save(reserva);
    }

    public void aceptarReserva(Reserva reserva) {
        Reserva reserva1 = reservaRepository.getById(reserva.getId());
        reserva1.setEstadoAceptado();
        reservaRepository.save(reserva1);
        Restaurant restaurant = restaurantService.getByEmail(reserva.getRestaurant().getEmail());
        restaurant.getMesa(reserva.getNroReferencia()).setMesaLibre(false);
        restaurantService.save(restaurant);
    }
}
