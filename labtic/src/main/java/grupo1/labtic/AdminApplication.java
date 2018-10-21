package grupo1.labtic;

import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.ui.admins.Administrar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class AdminApplication extends Application {

    private static ConfigurableApplicationContext context;

    private FXMLLoader fxmlLoader;

    private Parent root;

    private RestaurantService restaurantService;

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        context = SpringApplication.run(AdminApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
        restaurantService = context.getBean(RestaurantService.class);
        restaurantService.insertarGrupoDeComidas();
        restaurantService.insertarTiposDePagos();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        restaurantService.insertarGrupoDeComidas();
        fxmlLoader.setLocation(Administrar.class.getResource("AdminPortada.fxml"));
        root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        context.close();
    }
}