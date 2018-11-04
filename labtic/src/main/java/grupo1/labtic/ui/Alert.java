package grupo1.labtic.ui;

import grupo1.labtic.services.entities.Restaurant;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class Alert {
    public static void showAlert(String title, String contextText) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}

