package grupo1.labtic.ui.admins;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.UsuarioService;
import grupo1.labtic.services.exceptions.EmailInvalido;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class Administrar {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    UsuarioService usuarioService;

    @FXML
    private JFXButton backButton;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField rut;
    @FXML
    private JFXButton agregarRestaurante;
    @FXML
    private JFXPasswordField password;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView imagePortada;
    @FXML
    private AnchorPane imagePortadaContainer;
    @FXML
    private ImageView imgBack;

    public void initialize() {
        imagePortada.setPreserveRatio(false);
        imagePortada.fitHeightProperty().bind(imagePortadaContainer.heightProperty());
        imagePortada.fitWidthProperty().bind(imagePortadaContainer.widthProperty());
        Image img = new Image("file:src/main/resources/grupo1/labtic/ui/Imagenes/arreglo.jpg");
        imagePortada.setImage(img);
        Image iB = new Image("file:src/main/resources/grupo1/labtic/ui/admins/imgAdmin/back.png");
        imgBack.setImage(iB);
        Image iL = new Image("file:src/main/resources/grupo1/labtic/ui/Imagenes/yendoIcono.png");
        logo.setImage(iL);
    }

    @FXML
    public void agregar(ActionEvent actionEvent) {
        if (email.getText() == null || "".equals(email.getText()) || "".equals(password.getText()) ||
                password.getText() == null) {
            showAlert("Datos erroneos", "No se ingresaron los datos necesarios para completar el registro");
        } else try {
            String email = this.email.getText();
            usuarioService.isValidEmailAddress(email);
            String pass = password.getText();
            long rut1 = Long.valueOf(rut.getText());
            restaurantService.crearRestaurant(email, pass, rut1);
            showAlert("Restaurante agregado.", "Se agrego con exito el restaurante.");
            clean();
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        } catch (InvalidRestaurantInformation e) {
            showAlert("Informacion invalida!", "Se encontro un error en los datos ingresados.");
        } catch (RestaurantAlreadyExists e) {
            showAlert("Restaurante ya registrado", e.getMessage());
        } catch (EmailInvalido e) {
            showAlert("Email invalido", "El e-mail ingresado no es correcto.");
        } catch (NumberFormatException e) {
            showAlert(("RUT invalido"), "El campo RUT solo debe contener numeros.");
        }
        ;
    }

    private void clean() {
        email.setText(null);
        password.setText(null);
        rut.setText(null);
    }

    public void backBtn(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
