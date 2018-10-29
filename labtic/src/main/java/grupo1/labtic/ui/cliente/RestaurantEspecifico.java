package grupo1.labtic.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEspecifico {

    @FXML
    private TextArea descripcion;

    @FXML
    private Button reservar;

    @FXML
    private ComboBox<?> horaReserva;

    @FXML
    private ComboBox<?> cantPersonasReserva;

    @FXML
    private DatePicker fechaReserva;

    @FXML
    void reservar(ActionEvent event) {

    }

}
