package grupo1.labtic.ui.cliente;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.GrupoDeComida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.sql.Blob;
import java.util.ArrayList;
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
    private TableColumn imagen;
    @FXML
    private TableColumn nombreRestaurante;
    @FXML
    private TableColumn rating;
    @FXML
    private TableColumn descripcion;
    @FXML
    private TableColumn barrio;
    @FXML
    private TableColumn cocinas;


    @FXML
    void initialize() {
        assert zonasToFindTable != null : "fx:id=\"zonasToFindTable\" was not injected: check your FXML file 'principal.fxml'.";
        assert ciudadVieja != null : "fx:id=\"ciudadVieja\" was not injected: check your FXML file 'principal.fxml'.";
        assert centro != null : "fx:id=\"centro\" was not injected: check your FXML file 'principal.fxml'.";
        assert barrioSur != null : "fx:id=\"barrioSur\" was not injected: check your FXML file 'principal.fxml'.";
        assert cordon != null : "fx:id=\"cordon\" was not injected: check your FXML file 'principal.fxml'.";
        assert palermo != null : "fx:id=\"palermo\" was not injected: check your FXML file 'principal.fxml'.";
        assert parqueRodo != null : "fx:id=\"parqueRodo\" was not injected: check your FXML file 'principal.fxml'.";
        assert puntaCarretas != null : "fx:id=\"puntaCarretas\" was not injected: check your FXML file 'principal.fxml'.";
        assert pocitos != null : "fx:id=\"pocitos\" was not injected: check your FXML file 'principal.fxml'.";
        assert buceo != null : "fx:id=\"buceo\" was not injected: check your FXML file 'principal.fxml'.";
        assert parqueBatlle != null : "fx:id=\"parqueBatlle\" was not injected: check your FXML file 'principal.fxml'.";
        assert malvin != null : "fx:id=\"malvin\" was not injected: check your FXML file 'principal.fxml'.";
        assert puntaGorda != null : "fx:id=\"puntaGorda\" was not injected: check your FXML file 'principal.fxml'.";
        assert carrasco != null : "fx:id=\"carrasco\" was not injected: check your FXML file 'principal.fxml'.";
        assert maronias != null : "fx:id=\"maronias\" was not injected: check your FXML file 'principal.fxml'.";
        assert comidasToFindTable != null : "fx:id=\"comidasToFindTable\" was not injected: check your FXML file 'principal.fxml'.";
        assert sushi != null : "fx:id=\"sushi\" was not injected: check your FXML file 'principal.fxml'.";
        assert hamburgesas != null : "fx:id=\"hamburgesas\" was not injected: check your FXML file 'principal.fxml'.";
        assert ensaladas != null : "fx:id=\"ensaladas\" was not injected: check your FXML file 'principal.fxml'.";
        assert cafeteria != null : "fx:id=\"cafeteria\" was not injected: check your FXML file 'principal.fxml'.";
        assert parrilla != null : "fx:id=\"parrilla\" was not injected: check your FXML file 'principal.fxml'.";
        assert celiacos != null : "fx:id=\"celiacos\" was not injected: check your FXML file 'principal.fxml'.";
        assert chivitos != null : "fx:id=\"chivitos\" was not injected: check your FXML file 'principal.fxml'.";
        assert comidaChina != null : "fx:id=\"comidaChina\" was not injected: check your FXML file 'principal.fxml'.";
        assert comidaMexicana != null : "fx:id=\"comidaMexicana\" was not injected: check your FXML file 'principal.fxml'.";
        assert comidaVegetariana != null : "fx:id=\"comidaVegetariana\" was not injected: check your FXML file 'principal.fxml'.";
        assert comidaVegana != null : "fx:id=\"comidaVegana\" was not injected: check your FXML file 'principal.fxml'.";
        assert milanesas != null : "fx:id=\"milanesas\" was not injected: check your FXML file 'principal.fxml'.";
        assert pescadoMariscos != null : "fx:id=\"pescadoMariscos\" was not injected: check your FXML file 'principal.fxml'.";
        assert pizza != null : "fx:id=\"pizza\" was not injected: check your FXML file 'principal.fxml'.";
        assert sandwiches != null : "fx:id=\"sandwiches\" was not injected: check your FXML file 'principal.fxml'.";
        assert tartas != null : "fx:id=\"tartas\" was not injected: check your FXML file 'principal.fxml'.";
        assert wrap != null : "fx:id=\"wrap\" was not injected: check your FXML file 'principal.fxml'.";
        assert wok != null : "fx:id=\"wok\" was not injected: check your FXML file 'principal.fxml'.";
        assert restaurantToFind != null : "fx:id=\"restaurantToFind\" was not injected: check your FXML file 'principal.fxml'.";
        assert tableViewRestaurantes != null : "fx:id=\"tableViewRestaurantes\" was not injected: check your FXML file 'principal.fxml'.";
        assert imagen != null : "fx:id=\"imagen\" was not injected: check your FXML file 'principal.fxml'.";
        assert nombreRestaurante != null : "fx:id=\"nombreRestaurante\" was not injected: check your FXML file 'principal.fxml'.";
        assert rating != null : "fx:id=\"rating\" was not injected: check your FXML file 'principal.fxml'.";
        assert descripcion != null : "fx:id=\"descripcion\" was not injected: check your FXML file 'principal.fxml'.";
        assert barrio != null : "fx:id=\"barrio\" was not injected: check your FXML file 'principal.fxml'.";
        assert cocinas != null : "fx:id=\"cocinas\" was not injected: check your FXML file 'principal.fxml'.";

        nombreRestaurante.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("nombreRestaurant"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("descripcion"));
        barrio.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("barrio"));
        cocinas.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("cocinas"));
        imagen.setCellValueFactory(new PropertyValueFactory<Restaurant, ImageView>("imageView"));
        List<Restaurant> restaurantes = (List) repository.findAll();
        tableViewRestaurantes.setItems(FXCollections.observableList(restaurantes));
    }

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
            Restaurant resto = repository.findByNombreRestaurant(restaurantToFind.getText());
            if (resto != null) {

                ArrayList<Restaurant> restoFound = new ArrayList();
                restoFound.add(resto);

                ObservableList<Restaurant> observableList = FXCollections.observableArrayList();

                for(int i = 0 ; i < restoFound.size(); i++){
                    observableList.add(restoFound.get(i));
                }

                nombreRestaurante.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("NombreRestaurant"));
                barrio.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("barrio"));
                descripcion.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("descripcion"));

//                horario.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("horarioApertura" + "-" + "horarioCierre"));

                cocinas.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("listaComida"));

                tableViewRestaurantes.setItems(observableList);

            }
        }

    }


}

