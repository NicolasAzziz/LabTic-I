package grupo1.labtic.ui.admins;

import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.UsuarioService;
import grupo1.labtic.services.exceptions.EmailInvalido;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField rut;

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
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        } catch (InvalidRestaurantInformation e) {
            showAlert("Informacion invalida!", "Se encontro un error en los datos ingresados.");
        } catch (RestaurantAlreadyExists e) {
            showAlert("Restaurante ya registrado", e.getMessage());
        } catch(EmailInvalido e){
            showAlert("Email invalido", "El e-mail ingresado no es correcto.");
        }catch(NumberFormatException e){
            showAlert(("RUT invalido"), "El campo RUT solo debe contener numeros.");
        };
    }



    private void clean() {
        email.setText(null);
        password.setText(null);
        rut.setText(null);
    }

}
