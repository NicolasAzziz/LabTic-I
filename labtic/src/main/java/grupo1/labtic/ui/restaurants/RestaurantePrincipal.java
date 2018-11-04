package grupo1.labtic.ui.restaurants;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class RestaurantePrincipal {

    @FXML
    private TableView<?> reservasActivas;
    @FXML
    private TableView<?> reservasPendientes;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn nroMesa;
    @FXML
    private TableColumn hora;



}
