package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RestaurantRepository extends CrudRepository<Restaurant, String> {
    Usuario findOneByLogin(String login);
    Usuario getOneByLogin(String login);
    Restaurant getRestaurantById(long id);
    Restaurant getRestaurantByLogin(String login);
    Restaurant findOneById(long id);
    Restaurant findByNombreRestaurant(String nombreRestaurant);
    List<Restaurant> findAllByGrupoDeComidaList(List<GrupoDeComida> comidaPorGrupo);
    List<Restaurant> findAllByBarrio(List<String> restoPorZona);
    List<Restaurant> findAllByGrupoDeComidaListAndBarrio(List<String> porZona, List<GrupoDeComida> porBarrio);
    List<Restaurant> findAll();
}
