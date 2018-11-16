package grupo1.labtic.ui.restaurants;

import com.jfoenix.controls.JFXButton;
import grupo1.labtic.AppApplication;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.ui.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(AppApplication.getContext()::getBean);
        Parent root = null;
        try {
            root = loader.load(LoginController.class.getResourceAsStream("login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("¡Bienvenido!");
        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        double w = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getWidth();
        double h = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getHeight();
        stage.setScene(new Scene(root));
        stage.setHeight(h);
        stage.setWidth(w);
        stage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }


}
