package grupo1.labtic.ui.admins;

import grupo1.labtic.AdminApplication;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Admin;
import grupo1.labtic.services.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PortadaAdmin {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Restaurant> table;

    @FXML
    private TableColumn<Restaurant, String> col_nombre;

    @FXML
    private TableColumn<Restaurant, String> col_email;

    @FXML
    private TableColumn<Restaurant, String> col_rut;

    @FXML
    private TableColumn<Restaurant, String> col_barrio;

    @FXML
    private TableColumn<Restaurant, String> col_telefono;

    @FXML
    private TableColumn<Restaurant, String> col_direccion;

    @FXML
    private Button agregarRestaurant;

    @FXML
    private Circle circle2;

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane imageContainer;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_nombre != null : "fx:id=\"col_nombre\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_email != null : "fx:id=\"col_email\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_rut != null : "fx:id=\"col_rut\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_barrio != null : "fx:id=\"col_barrio\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_telefono != null : "fx:id=\"col_telefono\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_direccion != null : "fx:id=\"col_direccion\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert agregarRestaurant != null : "fx:id=\"agregarRestaurant\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert circle2 != null : "fx:id=\"circle2\" was not injected: check your FXML file 'adminPortada.fxml'.";

        col_nombre.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("nombreRestaurant"));
        col_email.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("email"));
        col_rut.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("rut"));
        col_barrio.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("barrio"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("telefono"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("direccion"));

        Iterable<Restaurant> listaRestaurantes = restaurantRepository.findAll();
        ObservableList<Restaurant> data = FXCollections.observableList((List) listaRestaurantes);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(imageContainer.widthProperty());
        //imageView.fitHeightProperty().bind(imageContainer.heightProperty());
        table.setItems(data);
    }

    @FXML
    public void agregarRestaurant(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(AdminApplication.getContext()::getBean);
            Parent root = loader.load(Administrar.class.getResourceAsStream("Admin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Nuevo Restaurant");
            stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
            stage.setScene(new Scene(root));
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


