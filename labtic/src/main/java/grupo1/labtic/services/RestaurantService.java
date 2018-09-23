package grupo1.labtic.services;

import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import grupo1.labtic.persistence.RestaurantRepository;


@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void addClient(String login, String password, String nombre, String direccion, int horarioCierre,
                          int horarioApertura, String barrio, String telefono, String formasDePago, String cocinas,
                          String descripcion)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (nombre == null || "".equals(nombre) || direccion == null || "".equals(direccion)
                || barrio == null || "".equals(barrio) || telefono == null || "".equals(telefono) || formasDePago == null || "".equals(formasDePago)
                || cocinas == null || "".equals(cocinas) || descripcion == null || "".equals(descripcion)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (restaurantRepository.findOneByLogin(nombre) != null) {

            throw new RestaurantAlreadyExists();
        }
        Restaurant oRestaurant = new Restaurant(login, password, nombre, direccion, horarioCierre, horarioApertura, barrio, telefono, formasDePago, cocinas, descripcion);

        Restaurant save = restaurantRepository.save(oRestaurant);

    }

    public void crearRestaurant(String login, String password)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (login == null || "".equals(login) || password == null || "".equals(password)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (restaurantRepository.findOneByLogin(login) != null) {

            throw new RestaurantAlreadyExists();
        }
        Restaurant oRestaurant = new Restaurant(login, password);

        Restaurant save = restaurantRepository.save(oRestaurant);


    }
    }
