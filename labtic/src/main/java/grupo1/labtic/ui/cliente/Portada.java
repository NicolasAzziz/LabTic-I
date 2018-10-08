package grupo1.labtic.ui.cliente;


import grupo1.labtic.ClienteApplication;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Usuario;
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
public class Portada {
    @Autowired
    private RestaurantRepository repo;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField pass;

    public void signIn(ActionEvent actionEvent) {
        if(usuario.getText()==null|| usuario.getText().equals("")||pass.getText()==null||pass.getText().equals("")){
            showAlert("Falta información", "No se ingresaron los datos requeridos");
        }
        else{
            try{
                String login = usuario.getText();
                String password = pass.getText();
                Usuario u = repo.findOneByLogin(login);
                if (u.getPassword().equals(password)){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setControllerFactory(ClienteApplication.getContext()::getBean);
                    Parent root = loader.load(Probando.class.getResourceAsStream("Principal.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                showAlert("Usuario no econtrado", "El usuario ingresado no existe");
            }
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
