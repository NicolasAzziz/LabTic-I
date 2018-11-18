package grupo1.labtic.persistence;

import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RestaurantRepository extends CrudRepository<Restaurant, String> {


    Restaurant findOneByEmail(String email);

//    Usuario findOneByEmail(String email);

    boolean existsByDireccion(String direccion);

    Restaurant getOneByEmail(String email);

    Restaurant getRestaurantById(long id);

    Restaurant getRestaurantByEmail(String email);

    Restaurant findOneById(long id);

    Restaurant findByNombreRestaurant(String nombreRestaurant);

    Restaurant findByRut(long RUT);

    Iterable<Restaurant> findAllByGrupoDeComidaList(List<GrupoDeComida> comidaPorGrupo);

    Iterable<Restaurant> findAllByBarrio(List<String> restoPorZona);

    Iterable<Restaurant> findAllByGrupoDeComidaListAndBarrio(List<String> restoPorZona, List<GrupoDeComida> comidaPorGrupo);

    Iterable<Restaurant> findAll();

}
