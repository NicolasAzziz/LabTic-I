package grupo1.labtic.ui.admins;

import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Admin;
import grupo1.labtic.services.exceptions.InvalidRestaurantInformation;
import grupo1.labtic.services.exceptions.RestaurantAlreadyExists;
import grupo1.labtic.ui.restaurants.SolicitarDatos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.TextField;

import java.awt.*;

@Component
public class Administrar {
    @Autowired
    private RestaurantService administrador;
    @FXML
    private TextField usuarioAAgregar;
    @FXML
    private TextField passAAgregar;


    public void agregar(ActionEvent actionEvent) {
        if(usuarioAAgregar.getText()==null||"".equals(usuarioAAgregar.getText())||"".equals(passAAgregar.getText())||
                passAAgregar.getText()==null){
            showAlert("Datos erroneos", "No se ingresaron los datos necesarios para completar el registro");
        }
        else {
            try {
                administrador.crearRestaurant(usuarioAAgregar.getText().toString(), passAAgregar.getText().toString());
                showAlert("Usuario agregado.", "Se agrego con exito el usuario.");
                clean();
            }catch(InvalidRestaurantInformation e){
                showAlert("Informacion invalida!","Se encontro un error en los datos ingresados."                        );
            }catch(RestaurantAlreadyExists e){
                showAlert("Usuario ya registrado", "El nombre de usuario ya a sido registrado en el sistema");
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
