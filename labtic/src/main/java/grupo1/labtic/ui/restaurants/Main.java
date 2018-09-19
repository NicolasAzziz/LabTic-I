package grupo1.labtic.ui.restaurants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    SolicitarDatos sd = new SolicitarDatos();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../../../../resources/solicitarDatos.fxml"));
        primaryStage.setTitle("Datos");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
            launch(args);
        }
}

