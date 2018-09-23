package grupo1.labtic.services;

import grupo1.labtic.services.entities.Mesa;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import grupo1.labtic.persistence.RestaurantRepository;
import java.util.List;


@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void addClient(int login, int password, String nombre, String direccion, int horarioCierre,
                          int horarioApertura, String barrio, String telefono, String formasDePago, String cocinas,
                          String descripcion, int mesa)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (nombre == null || "".equals(nombre) || direccion == null || "".equals(direccion)
                || barrio == null || "".equals(barrio) || telefono == null || "".equals(telefono) || formasDePago == null || "".equals(formasDePago)
                || cocinas == null || "".equals(cocinas) || descripcion == null || "".equals(descripcion)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (restaurantRepository.findOneByNombre(nombre) != null) {

            throw new RestaurantAlreadyExists();
        }
        Usuario usuario = new Usuario(login, password);
        Restaurant oRestaurant = new Restaurant(usuario, nombre, direccion, horarioCierre, horarioApertura, barrio, telefono, formasDePago, cocinas, descripcion, mesa);

        Restaurant save = restaurantRepository.save(oRestaurant);

    }
}
