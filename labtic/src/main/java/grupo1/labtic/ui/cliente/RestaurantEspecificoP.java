package grupo1.labtic.ui.cliente;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RestaurantEspecificoP {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text raiting;

    @FXML
    private Text horario;

    @FXML
    private Text nombre;

    @FXML
    private Text description;

    @FXML
    private Text comidas;

    @FXML
    private Text pagos;

    @FXML
    private Text precioMedio;

    @FXML
    private Text barrioRE;

    @FXML
    private Text direccion;

    @FXML
    private Text tel;

    @FXML
    private HBox imagenContainer;

    @FXML
    private ImageView imagenRE;

    @FXML
    private TableView<?> mesas;

    @FXML
    private TableColumn<?, ?> numeroMesa;

    @FXML
    private TableColumn<?, ?> sillas;

    @FXML
    private JFXButton cerrarSesion;

    @FXML
    private JFXButton reservas;

    @FXML
    private JFXButton ajustes;

    @FXML
    void ajustes(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void reservas(ActionEvent event) {

    }

    @FXML
    void solicitarMesa(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert raiting != null : "fx:id=\"raiting\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert horario != null : "fx:id=\"horario\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert nombre != null : "fx:id=\"nombre\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert comidas != null : "fx:id=\"comidas\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert pagos != null : "fx:id=\"pagos\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert precioMedio != null : "fx:id=\"precioMedio\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert barrioRE != null : "fx:id=\"barrioRE\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert direccion != null : "fx:id=\"direccion\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert tel != null : "fx:id=\"tel\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert imagenContainer != null : "fx:id=\"imagenContainer\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert imagenRE != null : "fx:id=\"imagenRE\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert mesas != null : "fx:id=\"mesas\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert numeroMesa != null : "fx:id=\"numeroMesa\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert sillas != null : "fx:id=\"sillas\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert cerrarSesion != null : "fx:id=\"cerrarSesion\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert reservas != null : "fx:id=\"reservas\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";
        assert ajustes != null : "fx:id=\"ajustes\" was not injected: check your FXML file 'restaurantEspecificoP.fxml'.";

    }
}
