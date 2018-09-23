package grupo1.labtic.ui.admins;

import grupo1.labtic.services.entities.Admin;
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
    private Admin administrador;
    @FXML
    private TextField usuarioAAgregar;
    @FXML
    private TextField passAAgregar;


    public void agregar(ActionEvent actionEvent) {
        if(usuarioAAgregar.getText()==null||"".equals(usuarioAAgregar.getText())||"".equals(passAAgregar.getText())||
                passAAgregar.getText()==null){
            showAlert("Datos erroneos", "No se ingresaron los datos necesarios para completar el registro");
        }
        else{
            administrador.crearRestaurant(usuarioAAgregar.getText().toString(), passAAgregar.getText().toString());
        }
    }

    public void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
