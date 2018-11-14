package grupo1.labtic.services;

import grupo1.labtic.persistence.ReservaRepository;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.restaurant.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public void nuevaReserva(Reserva reserva){
        reservaRepository.save(reserva);
    }
}
