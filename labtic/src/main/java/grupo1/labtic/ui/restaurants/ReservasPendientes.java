package grupo1.labtic.ui.restaurants;

import java.net.URL;
import java.util.ResourceBundle;

import grupo1.labtic.services.ReservaService;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.exceptions.MesaOcupada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class ReservasPendientes {

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
    private Text fechaYhora;

    @FXML
    private Text estadoMesa;

    @FXML
    private Text mesaNumero;

    @FXML
    private Text estadoReserva;

    private Reserva reserva;

    private RestaurantePrincipal restaurantePrincipal;

    void restaurantePrincipal(RestaurantePrincipal restaurantePrincipal){
        this.restaurantePrincipal = restaurantePrincipal;
    }
    @Autowired
    private ReservaService reservaService;


    public void setReserva(Reserva reserva){
        this.reserva = reserva;
        nombreCliente.setText(reserva.getCliente().getNombre());
        emailCliente.setText(reserva.getCliente().getEmail());
        mesaNumero.setText(reserva.getMesa().getNroReferencia().toString());
        sillas.setText(reserva.getMesa().getCantLugares().toString());
        estadoMesa.setText(reserva.getMesa().getEstado());
        estadoReserva.setText(reserva.getEstado());
        fechaYhora.setText(reserva.getFechaYhora().toString());
    }

    @FXML
    void aceptarReserva(ActionEvent event) {
        try {
            reservaService.aceptarReserva(reserva);
            showAlert("Reserva", "Se ha aceptado la reserva con exito.");
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            restaurantePrincipal.actualizarTablas();
        }catch(MesaOcupada e){
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    void rechazarReserva(ActionEvent event) {
        reservaService.rechazarReserva(reserva);
        showAlert("Reserva", "Se ha rechazado la reserva con exito.");
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        restaurantePrincipal.actualizarTablas();

    }

    @FXML
    void initialize() {
        assert nombreCliente != null : "fx:id=\"nombreCliente\" was not injected: check your FXML file 'reservasPendientes.fxml'.";
        assert emailCliente != null : "fx:id=\"emailCliente\" was not injected: check your FXML file 'reservasPendientes.fxml'.";
        assert sillas != null : "fx:id=\"sillas\" was not injected: check your FXML file 'reservasPendientes.fxml'.";
        assert estadoMesa != null : "fx:id=\"estadoMesa\" was not injected: check your FXML file 'reservasPendientes.fxml'.";
        assert mesaNumero != null : "fx:id=\"mesaNumero\" was not injected: check your FXML file 'reservasPendientes.fxml'.";
        assert estadoReserva != null : "fx:id=\"estadoReserva\" was not injected: check your FXML file 'reservasPendientes.fxml'.";

    }
}
