package grupo1.labtic.ui.cliente;


import grupo1.labtic.AdminApplication;
import grupo1.labtic.ClienteApplication;
import grupo1.labtic.persistence.ClienteRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.ui.admins.Administrar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Portada {
    @Autowired
    private ClienteRepository clienteRepository;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField pass;
    @FXML
    public void signIn(ActionEvent actionEvent) {
        if (usuario.getText() == null || usuario.getText().equals("") || pass.getText() == null || pass.getText().equals("")) {
            showAlert("Falta información", "No se ingresaron los datos requeridos");
        } else {
            try {
                String login = usuario.getText();
                String password = pass.getText();
                Cliente u = clienteRepository.findByEmail(login);
                if (u.getPassword().equals(password)) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setControllerFactory(ClienteApplication.getContext()::getBean);
                    Parent root = loader.load(Principal.class.getResourceAsStream("Principal.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Donde quiere comer?");
                    stage.setScene(new Scene(root));
                    ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
                    stage.show();
                }else{
                    showAlert("Contrasña incorrecta", "La contraseña ingresada no es correcta.");
                    pass.setText(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Usuario no econtrado", "El usuario ingresado no existe");
            }
        }
    }
    @FXML
    void registrate(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(ClienteApplication.getContext()::getBean);
            Parent root = loader.load(Registro.class.getResourceAsStream("registrarse.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registro de cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
