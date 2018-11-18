package grupo1.labtic.ui.admins;

import grupo1.labtic.services.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteEspecifico {

    @Autowired
    private PortadaAdmin portadaAdmin;

    @FXML
    private DatePicker fechaHasta;

    @FXML
    private Text horario;

    @FXML
    private DatePicker fechaDesde;

    @FXML
    private Button facturar;

    @FXML
    private Text direccion;

    @FXML
    private Text description;

    @FXML
    private Text comidas;

    @FXML
    private Text nombre;

    @FXML
    private Text barrioPM;

    @FXML
    private Text importe;

    @FXML
    private Text pagos;

    @FXML
    private HBox hBox;

    @FXML
    private Text rate;

    @FXML
    private ImageView logo;

    @FXML
    private Text tel;

    private Restaurant resto;


    @FXML
    void facturar(ActionEvent event) {

    }

    public void sendResto(Restaurant resto) {
        this.resto = resto;
    }


}
