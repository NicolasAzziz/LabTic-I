package grupo1.labtic;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.restaurant.comida.Cocina;
import grupo1.labtic.services.entities.restaurant.comida.Hamburgesas;
import grupo1.labtic.services.entities.restaurant.comida.Sushi;
import grupo1.labtic.services.entities.restaurant.pago.Efectivo;
import grupo1.labtic.services.entities.restaurant.pago.MetodoDePago;
import grupo1.labtic.services.entities.restaurant.pago.TarjetaCredito;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class RestaurantServiceTest {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    RestaurantRepository repository;

    @Test
    public void main(){
        try {
            restaurantService.crearRestaurant("TEST","V1");
        } catch (InvalidRestaurantInformation invalidRestaurantInformation) {
            invalidRestaurantInformation.printStackTrace();
        } catch (RestaurantAlreadyExists restaurantAlreadyExists) {
            restaurantAlreadyExists.printStackTrace();
        }
        List<Cocina> cocinas = new ArrayList<>();
        cocinas.add(new Sushi());
        cocinas.add(new Hamburgesas());

        List<MetodoDePago> metodoDePagoList = new ArrayList<>();
        metodoDePagoList.add(new TarjetaCredito());
        metodoDePagoList.add(new Efectivo());

        restaurantService.registrarDatosRestaurant(repository.findOneByLogin("TEST"),"res","tel","TESTDIRECCION", "barrio","horarioa","hcierre","des","web@correo.com","NuevaPass",3, metodoDePagoList, cocinas);

    }
}
