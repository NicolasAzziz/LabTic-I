package grupo1.labtic.services;

import grupo1.labtic.persistence.restaurantRepository.CocinaRepository;
import grupo1.labtic.persistence.restaurantRepository.ComidaRepository;
import grupo1.labtic.persistence.restaurantRepository.MesaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.persistence.restaurantRepository.MetodoDePagoRepository;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.services.entities.restaurant.Restaurant;
import grupo1.labtic.services.entities.restaurant.comida.Cocina;
import grupo1.labtic.services.entities.restaurant.comida.Comida;
import grupo1.labtic.services.entities.restaurant.pago.MetodoDePago;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService {
    @Autowired
    private ComidaRepository comidaRepository;
    @Autowired
    private CocinaRepository cocinaRepository;
    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;
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

    public void registrarDatosRestaurant(long id, String nombre, String telefono, String direccion,
                                         String barrio, String habre, String hcierra, String descripcion, String web,
                                         String nuevaPass, int nMesas, List<MetodoDePago> metodoDePagoList, List<Cocina> cocinaList, Comida comida){

        Restaurant restaurante = restaurantRepository.getRestaurantById(id);
        Comida c = comidaRepository.getComidaById(id);
        c.setCafeteria(comida.isCafeteria());
        c.setCeliacos(comida.isCeliacos());
        c.setChivitos(comida.isChivitos());
        c.setComidaChina(comida.isComidaChina());
        c.setComidaMexicana(comida.isComidaMexicana());
        c.setComidaVegana(comida.isComidaVegana());
        c.setComidaVegetariana(comida.isComidaVegetariana());
        c.setEnsaladas(comida.isEnsaladas());
        c.setHamburguesas(comida.isHamburguesas());
        c.setMilanesas(comida.isMilanesas());
        c.setParrilla(comida.isParrilla());
        c.setPescadoMariscos(comida.isPescadoMariscos());
        c.setPizza(comida.isPizza());
        c.setSandwiches(comida.isSandwiches());
        c.setSushi(comida.isSushi());
        c.setTartas(comida.isTartas());
        c.setWok(comida.isWok());
        c.setWraps(comida.isWraps());

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

        for( int i = 0; i<cocinaList.size(); i++){
             cocinaList.get(i).setRestaurantCocina(restaurante);
             cocinaList.get(i).setId(i);
             cocinaRepository.save(cocinaList.get(i));
        }

        for( int i = 0; i<metodoDePagoList.size(); i++){
            metodoDePagoList.get(i).setRestauranteMetodoDePago(restaurante);
            metodoDePagoList.get(i).setId(i);
            metodoDePagoRepository.save(metodoDePagoList.get(i));
        }
        comidaRepository.save(c);
    }
}
