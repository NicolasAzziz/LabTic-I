package grupo1.labtic;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.exceptions.DuplicateDireccion;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import grupo1.labtic.ui.admins.Administrar;
import javafx.stage.FileChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppApplication.class, Administrar.class})
public class RestaurantServiceTest extends javax.swing.JFrame {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    RestaurantRepository repository;

    @Test
    public void test() {
        Restaurant r = repository.getOneByEmail("r@t");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = new File("C:/Users/Usuario/OneDrive - Universidad de Montevideo/Imágenes/iCloud Photos/Downloads/3cc8ab6a-2698-45ec-b232-eb4ce1041af6.jpg");
        restaurantService.guardarImagen(r, imgFile);

    }

    @Test
    public void saveRestaurante() {
        Restaurant restaurant = new Restaurant("r@asdf", "123", 123L);
        try {
            restaurantService.crearRestaurant("r@asdf", "123", 123L);
        } catch (InvalidRestaurantInformation invalidRestaurantInformation) {
            invalidRestaurantInformation.printStackTrace();
        } catch (RestaurantAlreadyExists restaurantAlreadyExists) {
            restaurantAlreadyExists.printStackTrace();
        }
        try {
            restaurantService.registrarDatosRestaurant(restaurant, "midireccionasdfasdf", "08:00", "23:00", "Malvin", "123", "mi descasdfasdfasd", "asdf", "asdf", "1");
        } catch (DuplicateDireccion duplicateDireccion) {
            duplicateDireccion.printStackTrace();
        }

    }
}
