package grupo1.labtic;

import grupo1.labtic.ui.restaurants.SolicitarDatos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LabticApplication extends Application {


    public static void main(String[] args) {
        SpringApplication.run(LabticApplication.class, args);
    }

    private AnnotationConfigApplicationContext context;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        context = new AnnotationConfigApplicationContext(LabticApplication.class);

        FXMLLoader loader = new FXMLLoader(SolicitarDatos.class.getResource("grupo1/labtic/ui/restaurants/solicitarDatos.fxml"));
        loader.setControllerFactory(context::getBean);

        root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        context.close();
    }
}
