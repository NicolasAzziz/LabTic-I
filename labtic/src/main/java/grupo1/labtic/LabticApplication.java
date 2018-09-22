package grupo1.labtic;

import grupo1.labtic.ui.restaurants.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class LabticApplication extends Application {

    public static void main(String[] args) {
        SpringApplication.run(LabticApplication.class, args);

    }

    private AnnotationConfigApplicationContext context;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void stop() {
        context.close();
    }
}
