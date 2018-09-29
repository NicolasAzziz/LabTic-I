package grupo1.labtic;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class RestaurantServiceTest {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    RestaurantRepository repository;

    @Test
    public void main(){
        repository.deleteAll();
        try {
            restaurantService.crearRestaurant("hola","chau");
        } catch (InvalidRestaurantInformation invalidRestaurantInformation) {
            invalidRestaurantInformation.printStackTrace();
        } catch (RestaurantAlreadyExists restaurantAlreadyExists) {
            restaurantAlreadyExists.printStackTrace();
        }

        restaurantService.registrarDatosRestaurant(repository.findOneByLogin("hola"),"res","tel","dir", "barrio","horarioa","hcierre","des","web@correo.com","NuevaPass",3);

    }
}
