package grupo1.labtic.services;

import grupo1.labtic.persistence.ReservaRepository;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.exceptions.MesaOcupada;
import grupo1.labtic.services.exceptions.ReservaYaSolicitada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private RestaurantService restaurantService;


    public List<Reserva> reservasPendientes() {
        return (List) reservaRepository.getReservasByEstadoIs("Solicitado");
    }

    public void solicitarReserva(Cliente c, Restaurant r, int nroMesa) throws ReservaYaSolicitada {
        if (reservaRepository.existsByClienteAndRestaurantAndNroReferenciaAndEstado(c, r, nroMesa, "Solicitado")) {
            throw new ReservaYaSolicitada("Ya has solicitado una reserva a esa mesa.");
        }
        ;
        reservaRepository.save(new Reserva(c, r, nroMesa));
    }

    public List<Reserva> getReservasByCliente(Cliente cliente) {
        return (List) reservaRepository.getReservasByCliente_Email(cliente.getEmail());
    }

    public void save(Reserva reserva) {

        reservaRepository.save(reserva);
    }

    public void aceptarReserva(Reserva reserva) throws MesaOcupada {
        Reserva reserva1 = reservaRepository.getById(reserva.getId());
        reserva1.setEstadoAceptado();
        reservaRepository.save(reserva1);
        Restaurant restaurant = restaurantService.getByEmail(reserva.getRestaurant().getEmail());
        if (restaurant.getMesa(reserva.getNroReferencia()).isMesaLibre() == false)
            throw new MesaOcupada("La mesa solicitada esta ocupada.");
        restaurant.getMesa(reserva.getNroReferencia()).setMesaLibre(false);
        restaurant.setReservas(reserva1);
        restaurantService.save(restaurant);
    }

    public void rechazarReserva(Reserva reserva) {
        Reserva reserva1 = reservaRepository.getById(reserva.getId());
        reserva1.setEstadoRechazado();
        reservaRepository.save(reserva1);
    }

    public void finalizarReserva(Reserva reserva) {
        Reserva reserva1 = reservaRepository.getById(reserva.getId());
        reserva1.setEstadoFinalizado();
        reservaRepository.save(reserva1);
        Restaurant restaurant = restaurantService.getByEmail(reserva.getRestaurant().getEmail());
        restaurant.getMesa(reserva.getNroReferencia()).setMesaLibre(true);
        restaurantService.save(restaurant);
    }

    public List<Reserva> reservasActivas() {
        return (List) reservaRepository.getReservasByEstadoIs("Aceptado");
    }

    public Reserva getReservaByReserva(Reserva reserva) {
        return reservaRepository.getReservaById(reserva.getId());
    }

    public void cancelarReserva(Reserva reserva) {
        Reserva reserva1 = reservaRepository.getById(reserva.getId());
        reserva1.setEstadoCancelado();
        reservaRepository.save(reserva1);
    }

    public List<Reserva> getReservasByClienteAndEstadoSolicitado(Cliente cliente) {
        return (List) reservaRepository.getReservaByCliente_EmailAndEstado(cliente.getEmail(), "Solicitado");
    }

    public List<Reserva> getReservasByClienteAndEstadoAceptado(Cliente cliente) {
        return (List) reservaRepository.getReservaByCliente_EmailAndEstado(cliente.getEmail(), "Aceptado");
    }

    public Iterable<Reserva> getReservasByRestaurantAndEstadoAceptado(Restaurant restaurant) {
        return reservaRepository.getReservaByRestaurantAndEstado(restaurant, "Aceptado");
    }

    public Iterable<Reserva> getReservasByRestaurantAndEstadoFinalizado(Restaurant restaurant) {
        return reservaRepository.getReservaByRestaurantAndEstado(restaurant, "Finalizado");
    }
}
