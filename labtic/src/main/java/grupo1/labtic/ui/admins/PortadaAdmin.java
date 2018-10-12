package grupo1.labtic.ui.admins;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PortadaAdmin {

    public void agregarRestaurant(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root1 = null;
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1)); stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
