package grupo1.labtic.services;

import grupo1.labtic.persistence.MesaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.services.entities.restaurant.Restaurant;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MesaRepository mesaRepository;

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

    public void registrarDatosRestaurant(Restaurant restaurante, String nombre, String telefono, String direccion,String barrio,String habre,String hcierra,String descripcion,String web,String nuevaPass, int nMesas){
        restaurante = restaurantRepository.getRestaurantById(restaurante.getId());
        restaurante.setNombreRestaurant(nombre);
        restaurante.setTelefono(telefono);
        restaurante.setDireccion(direccion);
        restaurante.setBarrio(barrio);
        restaurante.setHorarioApertura(habre);
        restaurante.setHorarioCierre(hcierra);
        if ( descripcion !=null )
            restaurante.setDescripcion(descripcion);
        restaurante.setPassword(nuevaPass);
        if( web != null )
            restaurante.setEmail(web);
        restaurantRepository.save(restaurante);

        //TEMPORAL
        Mesa mesa = null;
        for(int i = 0; i<nMesas; i++){
            mesa = new Mesa(restaurante,i,1);
            mesaRepository.save(mesa);
        }


    }
}
