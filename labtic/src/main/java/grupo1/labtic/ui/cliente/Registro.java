package grupo1.labtic.ui.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import grupo1.labtic.AppApplication;
import grupo1.labtic.persistence.ClienteRepository;
import grupo1.labtic.services.ClienteService;
import grupo1.labtic.services.UsuarioService;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.exceptions.EmailInvalido;
import grupo1.labtic.ui.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class Registro {

    @FXML
    private JFXTextField emailCliente;
    @FXML
    private JFXTextField nombreCliente;
    @FXML
    private JFXButton registrar;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXPasswordField passwordRepeted;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @FXML
    void registrarCliente(ActionEvent event) {

        try {
            if (nombreCliente.getText() == null || nombreCliente.getText().equals("") || emailCliente.getText() == null
                    || emailCliente.getText().equals("") || password.getText() == null || password.getText().equals("")
                    || passwordRepeted.getText() == null || passwordRepeted.getText().equals("")) {
                showAlert("Datos faltantes!",
                        "No se ingresaron los datos necesarios para completar el ingreso.");
            } else {

                usuarioService.isValidEmailAddress(emailCliente.getText());

                Cliente cliente = clienteRepository.findByEmail(emailCliente.getText());
                if (cliente != null) {
                    showAlert("Email registrado", "Existe otro usuario con ese email.");
                } else {

                    if (!(password.getText().equals(passwordRepeted.getText()))) {
                        showAlert("Las contraseñas no coinciden",
                                "Las contrañeas no coinciden.");
                        cleanPasswords();
                    } else {
                        cliente = new Cliente(nombreCliente.getText(), emailCliente.getText(), password.getText());
                        clienteService.agregarCliente(cliente);
                        showAlert("Cliente agregado.", "Gracias por registrarse, " + nombreCliente.getText() + ".");
                        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
                        clean();
                    }
                }


            }
        } catch (EmailInvalido emailInvalido) {
            showAlert("Email invalido.", emailInvalido.getMessage());
        }
    }
        private void cleanPasswords() {
            password.setText(null);
            passwordRepeted.setText(null);
        }
        private void clean(){
            cleanPasswords();
            emailCliente.setText(null);
            nombreCliente.setText(null);
        }

    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(AppApplication.getContext()::getBean);
            Parent root = null;
            root = loader.load(LoginController.class.getResourceAsStream("login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bienvenido!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


