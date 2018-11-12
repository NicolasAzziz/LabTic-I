package grupo1.labtic.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import grupo1.labtic.AppApplication;
import grupo1.labtic.persistence.ClienteRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.ui.admins.Administrar;
import grupo1.labtic.ui.cliente.Principal;
import grupo1.labtic.ui.cliente.Registro;
import grupo1.labtic.ui.restaurants.RestaurantePrincipal;
import grupo1.labtic.ui.restaurants.SolicitarDatos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class LoginController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RestaurantRepository restoRepository;
    @FXML
    private Button registro;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton signIn;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton nuevoResto;

    @FXML
    public void signIn(ActionEvent actionEvent) {
        if (email.getText() == null || email.getText().equals("") || password.getText() == null || password.getText().equals("")) {
            showAlert("Falta información", "No se ingresaron los datos requeridos");
        } else {

            if(clienteRepository.findByEmail(email.getText()) != null){
                handleClienteLogin();
            }else if(restoRepository.findOneByEmail(email.getText()) != null){
                handleRestoLogin();
            }else{
                handleAdminLogin();
            }

        }
    }

    @FXML
    void registrate(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(AppApplication.getContext()::getBean);
            Parent root = loader.load(Registro.class.getResourceAsStream("registrarse.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registro de cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClienteLogin(){
        try {
            String login = email.getText();
            String pass = password.getText();
            Cliente u = clienteRepository.findByEmail(login);
            if (u.getPassword().equals(pass)) {
                FXMLLoader loader = new FXMLLoader();
                loader.setControllerFactory(AppApplication.getContext()::getBean);
                Parent root = loader.load(Principal.class.getResourceAsStream("Principal.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Donde quiere comer?");
                stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                stage.setScene(new Scene(root));
                stage.show();
                //((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
            }else{
                showAlert("Contraseña incorrecta", "La contraseña ingresada no es correcta.");
                password.setText(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Usuario no encontrado", "El usuario ingresado no existe");
        }
    }

    public void handleRestoLogin(){
        try {

            String login = email.getText();
            String pass = password.getText();
            Restaurant r = restoRepository.findOneByEmail(login);
            if (r.getPassword().equals(pass)) {
                if(r.getNombreRestaurant()==null||r.getNombreRestaurant().equals("")){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setControllerFactory(AppApplication.getContext()::getBean);

                    Parent root = loader.load(SolicitarDatos.class.getResourceAsStream("SolicitarDatos.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Ingrese los datos de su restaurante");
                    stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                    stage.setScene(new Scene(root));
                    stage.show();

                }
                else{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setControllerFactory(AppApplication.getContext()::getBean);
                    Parent root = loader.load(RestaurantePrincipal.class.getResourceAsStream("restaurantePrincipal.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("¡Bienvenido!");
                    stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }else{
                showAlert("Contrasña incorrecta", "La contraseña ingresada no es correcta.");
                password.setText(null);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            showAlert("Usuario no econtrado", "El email: " + this.email.getText() +" no existe en el sistema");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleAdminLogin(){

        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(AppApplication.getContext()::getBean);
        Parent root = null;
        try {
            root = loader.load(Administrar.class.getResourceAsStream("AdminPortada.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Donde quiere comer?");
        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }

}
