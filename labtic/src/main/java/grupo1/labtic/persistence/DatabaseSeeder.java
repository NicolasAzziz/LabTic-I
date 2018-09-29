package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseSeeder implements CommandLineRunner {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public DatabaseSeeder(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //List<Restaurant> restaurants = new ArrayList<Restaurant>();
        //restaurants.add(new Restaurant("Mc", "1234", "mcdonalds@gmail.com", "portones", 10, 22, "carrasco", "26001122", "tarjeta", "comida rapida", "blabla"));
        //restaurants.add(new Restaurant("Bk", "4321", "burgerking@gmail.com", "arocena", 10, 22, "carrasco", "26002211", "tarjeta", "comida rapida", "blabla"));

        restaurantRepository.save(new Restaurant("Mc", "1234", "mcdonalds@gmail.com", "portones", "10", "22", "carrasco", "26001122","blabla"));
        restaurantRepository.save(new Restaurant("Bk", "4321", "burgerking@gmail.com", "arocena", "10", "22", "carrasco", "26002211", "blabla"));
    }
}
