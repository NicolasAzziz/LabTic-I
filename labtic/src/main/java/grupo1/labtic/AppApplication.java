package grupo1.labtic;

import grupo1.labtic.ui.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppApplication extends Application {

    private static ConfigurableApplicationContext context;

    private FXMLLoader fxmlLoader;

    private Parent root;

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(AppApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader.setLocation(LoginController.class.getResource("login.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bienvenido!");
        primaryStage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        primaryStage.show();
    }

    @Override
    public void stop() {
        context.close();
    }
}
