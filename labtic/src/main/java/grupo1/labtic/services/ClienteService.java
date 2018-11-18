package grupo1.labtic.services;

import grupo1.labtic.persistence.ClienteRepository;
import grupo1.labtic.services.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public void agregarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }


    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}
