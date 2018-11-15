package grupo1.labtic.ui.restaurants;

import com.jfoenix.controls.JFXButton;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.restaurant.Mesa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class RestaurantePrincipal {

    @FXML
    private TableView<Reserva> reservasActivas;
    @FXML
    private TableView<Reserva> reservasPendientes;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn nroMesa;
    @FXML
    private TableColumn hora;
    @FXML
    private JFXButton cerrarSesion;
    @FXML
    private JFXButton ajustes;
    @FXML
    private Label nombreRestaurant;
    @FXML
    private TableView<Mesa> mesasLibres;
    @FXML
    private TableView<Mesa> mesasOcupadas;

    @FXML
    void ajustes(ActionEvent event) {
        //Cambiar datos
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }


}
