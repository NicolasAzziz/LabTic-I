package grupo1.labtic.ui.restaurants;

import grupo1.labtic.AdminApplication;
import grupo1.labtic.ClienteApplication;
import grupo1.labtic.RestaurantApplication;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.ui.cliente.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Inicio {

    @Autowired
    RestaurantRepository restaurantRepository;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField passField;

    public void contactarse(ActionEvent actionEvent) {
    }
    @FXML
    public void signInResto(ActionEvent actionEvent) {
        if (usuarioField.getText() == null || usuarioField.getText().equals("") || passField.getText() == null || passField.getText().equals("")) {
            showAlert("Falta información", "No se ingresaron los datos requeridos");
        } else {
            try {
                String login = usuarioField.getText();
                String password = passField.getText();
                Restaurant r = restaurantRepository.findOneByEmail(login);
                System.out.println(login);
                long id = r.getId();
                if (r.getPassword().equals(password)) {
                    if(r.getNombreRestaurant()==null||r.getNombreRestaurant().equals("")){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(RestaurantApplication.getContext()::getBean);
                        Parent root = loader.load(SolicitarDatos.class.getResourceAsStream("SolicitarDatos.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("Ingrese los datos de su restaurante");
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                    else{
                        //ingresar a pantalla principal del resto
                    }
                }else{
                    showAlert("Contrasña incorrecta", "La contraseña ingresada no es correcta.");
                    passField.setText(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Usuario no econtrado", "El usuario ingresado no existe");
            }
        }
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
