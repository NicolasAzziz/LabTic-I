package grupo1.labtic.services;

import grupo1.labtic.persistence.GrupoDeComidaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.persistence.TipoDePagoRepository;
import grupo1.labtic.persistence.restaurantRepository.MesaRepository;
import grupo1.labtic.persistence.restaurantRepository.MetodoDePagoRepository;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.services.entities.restaurant.TipoDePago;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService {
    @Autowired
    private GrupoDeComidaRepository grupoDeComidaRepository;
    @Autowired
    private TipoDePagoRepository tipoDePagoRepository;
    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MesaRepository mesaRepository;

    public void crearRestaurant(String email, String password, long rut)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (email == null || "".equals(email) || password == null || "".equals(password)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (restaurantRepository.findOneByEmail(email) != null) {

            throw new RestaurantAlreadyExists();
        }

        if (restaurantRepository.findByRut(rut) != null) {

            throw new RestaurantAlreadyExists();
        }

        Restaurant oRestaurant = new Restaurant(email, password, rut);

        Restaurant save = restaurantRepository.save(oRestaurant);


    }

//    public void registrarDatosRestaurant(long id, String nombre, String telefono, String direccion,
//                                         String barrio, String habre, String hcierra, String descripcion, String web,
//                                         String nuevaPass, int nMesas, int cantLugares){
//
//        Restaurant restaurante = restaurantRepository.getRestaurantById(id);
//        restaurante.setNombreRestaurant(nombre);
//        restaurante.setTelefono(telefono);
//        restaurante.setDireccion(direccion);
//        restaurante.setBarrio(barrio);
//        restaurante.setHorarioApertura(habre);
//        restaurante.setHorarioCierre(hcierra);
//        restaurante.setDescripcion(descripcion);
//        restaurante.setPassword(nuevaPass);
//        restaurante.setSitioWeb(web);
//        restaurantRepository.save(restaurante);
//
//        Mesa mesa = null;
//        for(int i = 0; i<nMesas; i++){
//            mesa = new Mesa(restaurante,i,cantLugares);
//            mesaRepository.save(mesa);
//        }
//    }

    public void registrarDatosRestaurant(long id, String nombre, String telefono, String direccion,
                                         String barrio, String habre, String hcierra, String descripcion, String web,
                                         String nuevaPass) {

        Restaurant restaurante = restaurantRepository.getRestaurantById(id);
        restaurante.setNombreRestaurant(nombre);
        restaurante.setTelefono(telefono);
        restaurante.setDireccion(direccion);
        restaurante.setBarrio(barrio);
        restaurante.setHorarioApertura(habre);
        restaurante.setHorarioCierre(hcierra);
        restaurante.setDescripcion(descripcion);
        restaurante.setPassword(nuevaPass);
        restaurante.setSitioWeb(web);
        restaurantRepository.save(restaurante);
    }
/*    public void save(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }*/

    public void setListaMesasRestaurante(String email, List<Mesa> mesasList) {
        Restaurant restaurante = restaurantRepository.getRestaurantByEmail(email);
        long id = restaurante.getId();

        mesaRepository.saveAll(mesasList);

    }

    public void setGrupoDeComida(String email, String grupoDeComida) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(email);
        GrupoDeComida grupoDeComida1 = grupoDeComidaRepository.getGrupoDeComidaByGrupo(grupoDeComida);
        restaurant.addGrupoDeComida(grupoDeComida1);
        restaurantRepository.save(restaurant);
    }

    public void setGrupoDeComidaList(String email, List<String> grupoDeComidaList) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(email);
        List<GrupoDeComida> grupoDeComida1 = grupoDeComidaRepository.getGrupoDeComidaByGrupo(grupoDeComidaList);
        restaurant.setGrupoDeComidaList(grupoDeComida1);
        restaurantRepository.save(restaurant);
    }

    public void setTipoDePago(String email, String nombreTipoDePago) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(email);
        TipoDePago nombreTipoDePago1 = tipoDePagoRepository.getByNombre(nombreTipoDePago);
        restaurant.addTipoDePago(nombreTipoDePago1);
        restaurantRepository.save(restaurant);
    }

    public void setTipoDePagoList(String email, List<String> nombreTipoDePagoList) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(email);
        List<TipoDePago> nombreTipoDePagoList1 = tipoDePagoRepository.getListByNombre(nombreTipoDePagoList);
        restaurant.setTipoDePagoList(nombreTipoDePagoList1);
        restaurantRepository.save(restaurant);
    }

    public void insertarGrupoDeComidas() {
        if (grupoDeComidaRepository.existsByGrupo("Wok") == false) {
            grupoDeComidaRepository.save(new GrupoDeComida("Hamburguesas"));
            grupoDeComidaRepository.save(new GrupoDeComida("Ensaladas"));
            grupoDeComidaRepository.save(new GrupoDeComida("Cafetería"));
            grupoDeComidaRepository.save(new GrupoDeComida("Parrilla"));
            grupoDeComidaRepository.save(new GrupoDeComida("Celíacos"));
            grupoDeComidaRepository.save(new GrupoDeComida("Chivitos"));
            grupoDeComidaRepository.save(new GrupoDeComida("Comida China"));
            grupoDeComidaRepository.save(new GrupoDeComida("Comida Mexicana"));
            grupoDeComidaRepository.save(new GrupoDeComida("Comida Vegetariana"));
            grupoDeComidaRepository.save(new GrupoDeComida("Comida Vegana"));
            grupoDeComidaRepository.save(new GrupoDeComida("Milanesas"));
            grupoDeComidaRepository.save(new GrupoDeComida("Pescado y Mariscos"));
            grupoDeComidaRepository.save(new GrupoDeComida("Pizza"));
            grupoDeComidaRepository.save(new GrupoDeComida("Sandwiches"));
            grupoDeComidaRepository.save(new GrupoDeComida("Tartas"));
            grupoDeComidaRepository.save(new GrupoDeComida("Wraps"));
            grupoDeComidaRepository.save(new GrupoDeComida("Wok"));
        }
    }

    public void insertarTiposDePagos() {
        if (tipoDePagoRepository.existsByNombre("Ticket Restaurante") == false) {
            tipoDePagoRepository.save(new TipoDePago("Tarjeta de Crédito"));
            tipoDePagoRepository.save(new TipoDePago("Tarjeta de Débito"));
            tipoDePagoRepository.save(new TipoDePago("Efectivo"));
            tipoDePagoRepository.save(new TipoDePago("Ticket Alimentación"));
            tipoDePagoRepository.save(new TipoDePago("Ticket Restaurante"));
        }
    }
}
