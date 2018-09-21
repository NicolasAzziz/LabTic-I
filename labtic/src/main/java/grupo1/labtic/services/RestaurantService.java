package grupo1.labtic.services;

import grupo1.labtic.services.entities.Mesa;
import grupo1.labtic.services.entities.Restaurant;
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

    public void addRestaurant(int login, int password, String nombre, String direccion, int horarioCierre,
                          int horarioApertura, String barrio, String telefono, String formasDePago, String cocinas,
                          String descripcion, List<Mesa> mesas)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        // Verifico que los datos ingresados no sean nulos

        if (nombre == null || "".equals(nombre) || direccion == null || "".equals(direccion)
                || barrio == null || "".equals(barrio) || telefono == null || "".equals(telefono) ||
                formasDePago == null || "".equals(formasDePago) || cocinas == null || "".equals(cocinas) ||
                descripcion == null || "".equals(descripcion) || mesas.isEmpty() == true) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el restaurant no existe

        if (restaurantRepository.findOneByNombre(nombre) != null) {

            throw new RestaurantAlreadyExists();
        }

        Restaurant oRestaurant = new Restaurant(login, password, nombre, direccion, horarioCierre,
                horarioApertura, barrio, telefono, formasDePago, cocinas, descripcion, mesas);

        restaurantRepository.save(oRestaurant);

    }
}
