package grupo1.labtic.ui.restaurants;

import grupo1.labtic.RestaurantApplication;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class Inicio {

    @Autowired
    RestaurantRepository restaurantRepository;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField passField;
    @FXML
    private ImageView imageSignIn;
    @FXML
    private ImageView imagePortada;
    @FXML
    private AnchorPane imagePortadaContainer;
    @FXML
    private AnchorPane imageSignInContainer;

    private static Restaurant restaurant;

    public void  initialize() {

        imagePortada.setPreserveRatio(true);
        imagePortada.fitWidthProperty().bind(imagePortadaContainer.widthProperty());
//        imagePortada.fitHeightProperty().bind(imagePortadaContainer.heightProperty());

        imageSignIn.setPreserveRatio(false);
        imageSignIn.fitHeightProperty().bind(imageSignInContainer.heightProperty());
        imageSignIn.fitWidthProperty().bind(imageSignInContainer.widthProperty());



    }

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
                restaurant = restaurantRepository.findOneByEmail(login);
                System.out.println(login);
                long id = restaurant.getId();
                if (restaurant.getPassword().equals(password)) {
                    if(restaurant.getNombreRestaurant()==null||restaurant.getNombreRestaurant().equals("")){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(RestaurantApplication.getContext()::getBean);
                        Parent root = loader.load(getClass().getResourceAsStream("SolicitarDatos.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("Ingrese los datos de su restaurante");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        stage.setScene(new Scene(root));
                        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
                        stage.show();

                    }
                    else{
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(RestaurantApplication.getContext()::getBean);
                        Parent root = loader.load(getClass().getResourceAsStream("restaurantePrincipal.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("¡Bienvenido!");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        stage.setScene(new Scene(root));
                        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
                    }
                }else{
                    showAlert("Contrasña incorrecta", "La contraseña ingresada no es correcta.");
                    passField.setText(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Usuario no econtrado", "El email: " + this.usuarioField.getText() +" no existe en el sistema");
            }
        }
    }
    public static Restaurant getRestaurant(){
        return restaurant;
    }


}
