package grupo1.labtic.ui.cliente;

import com.jfoenix.controls.JFXButton;
import grupo1.labtic.services.ReservaService;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.Mesa;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class ReservaEspecifica {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text restaurantNombre;

    @FXML
    private Text mesaNumero;

    @FXML
    private Text sillas;

    @FXML
    private Text direccion;

    @FXML
    private Text estadoReserva;

    @FXML
    private Text emailRestaurant;

    @FXML
    private Text telefono;

    @FXML
    private Text fechaYhora;

    private Reserva reserva;
    @Autowired
    private ReservaService reservaService;

    @FXML
    private JFXButton cancelarReservaButton;

    private VerReservas verReservas;
    @FXML
    private ImageView imagePortada;
    @FXML
    private AnchorPane imagePortadaContainer;

    public void initialize() {
            assert restaurantNombre != null : "fx:id=\"restaurantNombre\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
            assert mesaNumero != null : "fx:id=\"mesaNumero\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
            assert sillas != null : "fx:id=\"sillas\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
            assert direccion != null : "fx:id=\"direccion\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
            assert estadoReserva != null : "fx:id=\"estadoReserva\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
            assert emailRestaurant != null : "fx:id=\"emailRestaurant\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
            assert telefono != null : "fx:id=\"telefono\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
            assert fechaYhora != null : "fx:id=\"fechaYhora\" was not injected: check your FXML file 'ReservaEspecifica.fxml'.";
        imagePortada.setPreserveRatio(false);
        imagePortada.fitHeightProperty().bind(imagePortadaContainer.heightProperty());
        imagePortada.fitWidthProperty().bind(imagePortadaContainer.widthProperty());
        Image img = new Image("file:src/main/resources/grupo1/labtic/ui/restaurants/imgRestaurante/arreglo.jpg");
        imagePortada.setImage(img);
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        restaurantNombre.setText(reserva.getNombreRestaurant());
        mesaNumero.setText(reserva.getMesa().getNroReferencia().toString());
        sillas.setText(reserva.getMesa().getCantLugares().toString());
        direccion.setText(reserva.getRestaurant().getDireccion());
        estadoReserva.setText(reserva.getEstado());
        emailRestaurant.setText(reserva.getRestaurant().getEmail());
        telefono.setText(reserva.getRestaurant().getTelefono());
        fechaYhora.setText(reserva.getFechaYhoraString());
        if (!reserva.getEstado().equals("Solicitado")) {
            cancelarReservaButton.setDisable(true);
        } else {
            cancelarReservaButton.setDisable(false);
        }
    }

    @FXML
    void cancelarReserva(ActionEvent event) {
        reservaService.cancelarReserva(reserva);
        showAlert("Reserva", "Se ha cancelado la reserva.");
        verReservas.refreshTabla(event);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    public void actualizarReserva(ActionEvent event) {
        setReserva(reservaService.getReservaByReserva(reserva));
    }

    public void setVerReservas(VerReservas verReservas) {
        this.verReservas = verReservas;
    }
}
