package grupo1.labtic.ui.restaurants;

import grupo1.labtic.services.ReservaService;
import grupo1.labtic.services.entities.Reserva;
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

import java.net.URL;
import java.util.ResourceBundle;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class ReservasActivas {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text nombreCliente;

    @FXML
    private Text emailCliente;

    @FXML
    private Text sillas;

    @FXML
    private Text hora;

    @FXML
    private Text estadoMesa;

    @FXML
    private Text mesaNumero;

    @FXML
    private Text estadoReserva;

    private Reserva reserva;

    private RestaurantePrincipal restaurantePrincipal;
    @FXML
    private ImageView imagePortada;
    @FXML
    private AnchorPane imagePortadaContainer;

    @Autowired
    private ReservaService reservaService;

    public void initialize() {
        imagePortada.setPreserveRatio(false);
        imagePortada.fitHeightProperty().bind(imagePortadaContainer.heightProperty());
        imagePortada.fitWidthProperty().bind(imagePortadaContainer.widthProperty());
        Image img = new Image("file:src/main/resources/grupo1/labtic/ui/restaurants/imgRestaurante/arreglo.jpg");
        imagePortada.setImage(img);
    }


    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        nombreCliente.setText(reserva.getCliente().getNombre());
        emailCliente.setText(reserva.getCliente().getEmail());
        mesaNumero.setText(reserva.getMesa().getNroReferencia().toString());
        sillas.setText(reserva.getMesa().getCantLugares().toString());
        estadoMesa.setText(reserva.getMesa().getEstado());
        estadoReserva.setText(reserva.getEstado());
        hora.setText(reserva.getHoraString());
    }

    @FXML
    void finalizarReserva(ActionEvent event) {
        reservaService.finalizarReserva(reserva);
        showAlert("Reserva", "Se ha finalizado la reserva con exito.");
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        restaurantePrincipal.actualizarTablas();

    }

    public void restaurantPrincipal(RestaurantePrincipal restaurantePrincipal) {
        this.restaurantePrincipal = restaurantePrincipal;
    }
}
