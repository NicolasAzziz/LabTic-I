package grupo1.labtic.ui.restaurants;

import grupo1.labtic.services.entities.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


}
