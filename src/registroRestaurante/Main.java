package registroRestaurante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("registroRestaurante.fxml"));
        primaryStage.setTitle("Solicitud de registro");
        primaryStage.setScene(new Scene(root, 371, 313));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
