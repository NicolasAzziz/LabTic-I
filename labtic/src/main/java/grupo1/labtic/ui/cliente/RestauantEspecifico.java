package grupo1.labtic.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class RestauantEspecifico {

    @FXML
    private TextArea descripcion;

    @FXML
    private Button reservar;

    @FXML
    private ComboBox<String> horaReserva;

    @FXML
    private ComboBox<String> cantPersonasReserva;

    @FXML
    private DatePicker fechaReserva;

    @FXML
    public void reservar(ActionEvent actionEvent) {


    }
}
