package grupo1.labtic.ui.cliente;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Principal {

    @Autowired
    private RestaurantRepository repository;

    @FXML
    private MenuButton zonasToFindTable;
    @FXML
    private MenuButton comidasToFindTable;

    @FXML
    private TextField restaurantToFind;

    @FXML
    private CheckMenuItem puntaCarretas;
    @FXML
    private CheckMenuItem palermo;
    @FXML
    private CheckMenuItem parqueRodo;
    @FXML
    private CheckMenuItem maronias;
    @FXML
    private CheckMenuItem cordon;
    @FXML
    private CheckMenuItem buceo;
    @FXML
    private CheckMenuItem malvin;
    @FXML
    private CheckMenuItem ciudadVieja;
    @FXML
    private CheckMenuItem centro;
    @FXML
    private CheckMenuItem pocitos;
    @FXML
    private CheckMenuItem barrioSur;
    @FXML
    private CheckMenuItem parqueBatlle;
    @FXML
    private CheckMenuItem puntaGorda;
    @FXML
    private CheckMenuItem carrasco;

    @FXML
    private CheckMenuItem comidaVegana;
    @FXML
    private CheckMenuItem parrilla;
    @FXML
    private CheckMenuItem sandwiches;
    @FXML
    private CheckMenuItem cafeteria;
    @FXML
    private CheckMenuItem comidaChina;
    @FXML
    private CheckMenuItem celiacos;
    @FXML
    private CheckMenuItem comidaMexicana;
    @FXML
    private CheckMenuItem wok;
    @FXML
    private CheckMenuItem hamburgesas;
    @FXML
    private CheckMenuItem pizza;
    @FXML
    private CheckMenuItem chivitos;
    @FXML
    private CheckMenuItem sushi;
    @FXML
    private CheckMenuItem tartas;
    @FXML
    private CheckMenuItem pescadoMariscos;
    @FXML
    private CheckMenuItem comidaVegetariana;
    @FXML
    private CheckMenuItem ensaladas;
    @FXML
    private CheckMenuItem wrap;
    @FXML
    private CheckMenuItem milanesas;


    @FXML
    private TableView<Restaurant> tableViewRestaurantes;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn horario;
    @FXML
    private TableColumn comidas;
    @FXML
    private TableColumn zona;

    @FXML
    public void findTable(ActionEvent event) {

        List<String> selectedItemsComidas = comidasToFindTable.getItems().stream().filter(item ->
                CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText).collect(Collectors.toList());

        List<String> selectedBarrios = zonasToFindTable.getItems().stream().filter(item ->
                CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText).collect(Collectors.toList());

        if (restaurantToFind.getText() == null || restaurantToFind.getText().equals("")) {
            if (selectedBarrios.isEmpty() == true && selectedItemsComidas.isEmpty() == true) {


            } else if (selectedBarrios.isEmpty() == true && selectedItemsComidas.isEmpty() == false) {

            } else if (selectedBarrios.isEmpty() == false && selectedItemsComidas.isEmpty() == true) {

            } else {

            }

        } else {
            Restaurant rest = repository.findByNombreRestaurant(restaurantToFind.getText());
            if (rest != null) {

                Restaurant resto = repository.findByNombreRestaurant(restaurantToFind.getText());

                nombre.setText(resto.getNombreRestaurant());
                horario.setText(resto.getHorarioApertura() + " - " + resto.getHorarioCierre());
                String var = null;
                for (int i = 0; i < resto.getGrupoDeComidaList().size(); i++) {
                    var = var + " " + resto.getGrupoDeComidaList().get(i);
                }
                comidas.setText(var);
                zona.setText(resto.getBarrio());
            }
        }

    }

    public ObservableList<Restaurant> getRestaurants() {
        ObservableList<Restaurant> restaurants = FXCollections.observableArrayList();
        restaurants.add(repository.findByNombreRestaurant(restaurantToFind.getText()));
        return restaurants;
    }

}

