package grupo1.labtic;

import grupo1.labtic.ui.restaurants.SolicitarDatos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class RestaurantApplication extends Application {

    private  static ConfigurableApplicationContext context;


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
        context = SpringApplication.run(RestaurantApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader.setLocation(SolicitarDatos.class.getResource("inicio.fxml"));

        root = fxmlLoader.load();
        primaryStage.setTitle("Yendo Restaurant");
        primaryStage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        context.close();
    }

}