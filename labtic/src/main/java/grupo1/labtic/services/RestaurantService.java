package grupo1.labtic.services;

import grupo1.labtic.persistence.BarrioRepository;
import grupo1.labtic.persistence.GrupoDeComidaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.persistence.TipoDePagoRepository;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.Barrio;
import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.services.entities.restaurant.TipoDePago;
import grupo1.labtic.services.exceptions.DuplicateDireccion;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Service
public class RestaurantService {
    @Autowired
    private GrupoDeComidaRepository grupoDeComidaRepository;
    @Autowired
    private TipoDePagoRepository tipoDePagoRepository;
    @Autowired
    private BarrioRepository barrioRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    public void crearRestaurant(String email, String password, long rut)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (email == null || "".equals(email) || password == null || "".equals(password)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (restaurantRepository.findOneByEmail(email) != null) {

            throw new RestaurantAlreadyExists("Ya existe un restaurante con el email: " + email);
        }

        if (restaurantRepository.findByRut(rut) != null) {

            throw new RestaurantAlreadyExists("RUT ya existente.");
        }

        Restaurant oRestaurant = new Restaurant(email, password, rut);

        Restaurant save = restaurantRepository.save(oRestaurant);


    }

    public void crearRestaurant(String email, String password)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (email == null || "".equals(email) || password == null || "".equals(password)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (restaurantRepository.findOneByEmail(email) != null) {

            throw new RestaurantAlreadyExists("Ya existe un usuario con el email: " + email);
        }


        Restaurant oRestaurant = new Restaurant(email, password);

        Restaurant save = restaurantRepository.save(oRestaurant);


    }

    public void setPrecioMedio(Restaurant restaurante, String precioMedio) {
        Restaurant restaurant = restaurantRepository.getOneByEmail(restaurante.getEmail());
        restaurant.setPrecioMedio(precioMedio);
        restaurantRepository.save(restaurant);
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

    public void registrarDatosRestaurant(Restaurant restaurante, String nombre, String telefono, String direccion,
                                         String barrio, String habre, String hcierra, String descripcion, String web,
                                         String nuevaPass) throws DuplicateDireccion {
        if (restaurantRepository.existsByDireccion(direccion)) {
            throw new DuplicateDireccion("La dirección ingresada ya esta registrada.");
        }
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(restaurante.getEmail());
        restaurant.setNombreRestaurant(nombre);
        restaurant.setTelefono(telefono);
        restaurant.setDireccion(direccion);
        restaurant.setBarrio(barrio);
        restaurant.setHorarioApertura(habre);
        restaurant.setHorarioCierre(hcierra);
        restaurant.setDescripcion(descripcion);
        restaurant.setPassword(nuevaPass);
        restaurant.setSitioWeb(web);
        restaurantRepository.save(restaurant);
    }
/*    public void save(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }*/

    public void setListaMesasRestaurante(Restaurant restaurante, List<Mesa> mesasList) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(restaurante.getEmail());

        restaurant.setMesas(mesasList);

        restaurantRepository.save(restaurant);

    }


    public void setGrupoDeComidaList(Restaurant restaurante, List<String> grupoDeComidaList) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(restaurante.getEmail());
        List<GrupoDeComida> grupoDeComida1 = grupoDeComidaRepository.getGrupoDeComidaByGrupo(grupoDeComidaList);
        restaurant.setGrupoDeComidaList(grupoDeComida1);
        restaurantRepository.save(restaurant);
    }

    public void setTipoDePago(Restaurant restaurant, String nombreTipoDePago) {
        TipoDePago nombreTipoDePago1 = tipoDePagoRepository.getByNombre(nombreTipoDePago);
        restaurant.addTipoDePago(nombreTipoDePago1);
        restaurantRepository.save(restaurant);
    }

    public void setTipoDePagoList(Restaurant restaurante, List<String> nombreTipoDePagoList) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(restaurante.getEmail());
        List<TipoDePago> nombreTipoDePagoList1 = tipoDePagoRepository.getListByNombre(nombreTipoDePagoList);
        restaurant.setTipoDePagoList(nombreTipoDePagoList1);
        restaurantRepository.save(restaurant);
    }

    public void guardarImagen(Restaurant restaurante, File imgFile) {
        Restaurant restaurant = restaurantRepository.getRestaurantByEmail(restaurante.getEmail());
        byte[] data = null;
        try {
            FileInputStream fis = new FileInputStream(imgFile);
            data = Files.readAllBytes(imgFile.toPath());
        } catch (IOException e) {
            e.getStackTrace();
        }
        restaurant.setImagen(data);
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

    public void insertarBarrios() {
        if (barrioRepository.existsByBarrio("Carrasco") == false) {
            barrioRepository.save(new Barrio("Ciudad Vieja"));
            barrioRepository.save(new Barrio("Centro"));
            barrioRepository.save(new Barrio("Palermo"));
            barrioRepository.save(new Barrio("Punta Carretas"));
            barrioRepository.save(new Barrio("Cordón"));
            barrioRepository.save(new Barrio("Buceo"));
            barrioRepository.save(new Barrio("Malvin"));
            barrioRepository.save(new Barrio("Pocitos"));
            barrioRepository.save(new Barrio("Parque Batlle"));
            barrioRepository.save(new Barrio("Punta Gorda"));
            barrioRepository.save(new Barrio("Carrasco"));
            barrioRepository.save(new Barrio("Maroñas"));
        }

    }

    public Restaurant getByEmail(String email) {
        return restaurantRepository.getOneByEmail(email);
    }

    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public List<Mesa> mesasLibres(Restaurant restaurant) {
        Restaurant restaurant1 = restaurantRepository.getRestaurantByEmail(restaurant.getEmail());
        List<Mesa> mesas = restaurant1.getMesas();
        List<Mesa> mesasLibres = new ArrayList<>();
        for (Mesa mesa:mesas
             ) {
            if(mesa.isMesaLibre())
                mesasLibres.add(mesa);
        }
        return mesasLibres;
    }

    public List<Mesa> mesasOcupadas(Restaurant restaurant) {
        Restaurant restaurant1 = restaurantRepository.getRestaurantByEmail(restaurant.getEmail());
        List<Mesa> mesas = restaurant1.getMesas();
        List<Mesa> mesasOcupadas = new ArrayList<>();
        for (Mesa mesa:mesas
        ) {
            if(mesa.isMesaLibre() == false)
                mesasOcupadas.add(mesa);
        }
        return mesasOcupadas;
    }
}
