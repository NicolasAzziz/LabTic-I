package grupo1.labtic.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import grupo1.labtic.AppApplication;
import grupo1.labtic.persistence.AdminRepository;
import grupo1.labtic.persistence.ClienteRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Admin;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    @Autowired
    private AdminRepository adminRepository;
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
    private ImageView imagePortada;
    @FXML
    private AnchorPane imagePortadaContainer;
    @FXML
    private ImageView logo;

    public void initialize() {
        imagePortada.setPreserveRatio(false);
        imagePortada.fitHeightProperty().bind(imagePortadaContainer.heightProperty());
        imagePortada.fitWidthProperty().bind(imagePortadaContainer.widthProperty());
        Image img = new Image("file:src/main/resources/grupo1/labtic/ui/Imagenes/arreglo.jpg");
        imagePortada.setImage(img);
        Image iL = new Image("file:src/main/resources/grupo1/labtic/ui/Imagenes/yendoIcono.png");
        logo.setImage(iL);
    }

    @FXML
    public void signIn(ActionEvent actionEvent) {
        if (email.getText() == null || email.getText().equals("") || password.getText() == null || password.getText().equals("")) {
            showAlert("Falta información", "No se ingresaron los datos requeridos");
        } else {

            if (clienteRepository.findByEmail(email.getText()) != null) {
                //handleClienteLogin();
                try {
                    String login = email.getText();
                    String pass = password.getText();
                    Cliente u = clienteRepository.findByEmail(login);
                    if (u.getPassword().equals(pass)) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(AppApplication.getContext()::getBean);
                        Parent root = loader.load(Principal.class.getResourceAsStream("principalCliente.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Donde quiere comer?");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        double w = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getWidth();
                        double h = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getHeight();
                        stage.setScene(new Scene(root));
                        stage.setHeight(h);
                        stage.setWidth(w);
                        stage.show();
                        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
                        Principal controler = loader.<Principal>getController();
                        controler.setCliente(u);
                    } else {
                        showAlert("Contraseña incorrecta", "La contraseña ingresada no es correcta.");
                        password.setText(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Usuario no encontrado", "El usuario ingresado no existe");
                }
            } else if (restoRepository.findOneByEmail(email.getText()) != null) {
                //handleRestoLogin();

                String login = email.getText();
                String pass = password.getText();
                Restaurant r = restoRepository.findOneByEmail(login);
                if (r.getPassword().equals(pass)) {
                    if (r.getNombreRestaurant() == null || r.getNombreRestaurant().equals("")) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(AppApplication.getContext()::getBean);

                        Parent root = null;
                        try {
                            root = loader.load(SolicitarDatos.class.getResourceAsStream("SolicitarDatos.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Stage stage = new Stage();
                        stage.setTitle("Ingrese los datos de su restaurante");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        double w = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getWidth();
                        double h = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getHeight();
                        stage.setScene(new Scene(root));
                        stage.setHeight(h);
                        stage.setWidth(w);
                        stage.show();
                        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
                        SolicitarDatos controler = loader.<SolicitarDatos>getController();
                        controler.restaurante(r);
                    } else {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(AppApplication.getContext()::getBean);
                        Parent root = null;
                        try {
                            root = loader.load(RestaurantePrincipal.class.getResourceAsStream("principalRestaurante.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        stage.setTitle("¡Bienvenido!");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        double w = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getWidth();
                        double h = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getHeight();
                        stage.setScene(new Scene(root));
                        stage.setHeight(h);
                        stage.setWidth(w);
                        stage.show();
                        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
                    }
                } else {
                    showAlert("Contrasña incorrecta", "La contraseña ingresada no es correcta.");
                    password.setText(null);
                }

            } else if (adminRepository.existsByEmail(email.getText())) {
                //handleAdminLogin();
                Admin admin = adminRepository.getByEmail(email.getText());
                if (admin.getPassword().equals(password.getText())) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setControllerFactory(AppApplication.getContext()::getBean);
                    Parent root = null;
                    try {
                        root = loader.load(Administrar.class.getResourceAsStream("adminPortada.fxml"));
                    } catch (IOException e) {
                        e.fillInStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Yendo");
                    stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                    double w = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getWidth();
                    double h = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getHeight();
                    stage.setScene(new Scene(root));
                    stage.setHeight(h);
                    stage.setWidth(w);
                    stage.show();
                    ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
                } else {
                    showAlert("Contrasña incorrecta", "La contraseña ingresada no es correcta.");
                    password.setText(null);
                }
            } else {
                showAlert("Usuario no econtrado", "El email: " + this.email.getText() + " no existe en el sistema");

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
            stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
            double w = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getWidth();
            double h = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).getHeight();
            stage.setScene(new Scene(root));
            stage.setHeight(h);
            stage.setWidth(w);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}