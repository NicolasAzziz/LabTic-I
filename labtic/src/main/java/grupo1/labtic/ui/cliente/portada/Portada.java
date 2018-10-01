package grupo1.labtic.ui.cliente.portada;


import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;


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
                    //cambiar la escena
                }
            }
            catch (Exception e){
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
