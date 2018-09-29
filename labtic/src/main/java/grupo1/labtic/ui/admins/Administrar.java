package grupo1.labtic.ui.admins;

import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.TextField;

@Component
public class Administrar {
    @Autowired
    private RestaurantService restaurantService ;
    @FXML
    private TextField usuarioAAgregar;
    @FXML
    private PasswordField passAAgregar;

    @FXML
    public void agregar(ActionEvent actionEvent) {
        if(usuarioAAgregar.getText()==null||"".equals(usuarioAAgregar.getText())||"".equals(passAAgregar.getText())||
                passAAgregar.getText()==null){
            showAlert("Datos erroneos", "No se ingresaron los datos necesarios para completar el registro");
        }
        else {
            try {
                String usuario = usuarioAAgregar.getText();
                String pass = passAAgregar.getText();
                restaurantService.crearRestaurant(usuario, pass);
                showAlert("Usuario agregado.", "Se agrego con exito el usuario.");
                clean();
            }catch(InvalidRestaurantInformation e){
                showAlert("Informacion invalida!","Se encontro un error en los datos ingresados."                        );
            }catch(RestaurantAlreadyExists e){
                showAlert("Usuario ya registrado", "El nombre de usuario ya ha sido registrado en el sistema");
            }
        }
    }

    private void clean() {
        usuarioAAgregar.setText(null);
        passAAgregar.setText(null);
    }

    public void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
