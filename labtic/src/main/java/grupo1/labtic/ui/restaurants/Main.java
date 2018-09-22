package grupo1.labtic.ui.restaurants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    SolicitarDatos sd = new SolicitarDatos();

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = getClass().getResource("../../../../resources/solicitarDatos.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Datos");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
            launch(args);
        }
}

