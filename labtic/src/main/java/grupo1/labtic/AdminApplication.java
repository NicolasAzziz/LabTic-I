package grupo1.labtic;

import grupo1.labtic.ui.admins.Administrar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AdminApplication extends Application  {

    private ConfigurableApplicationContext context;

    private FXMLLoader fxmlLoader;

    private Parent root;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(AdminApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation(Administrar.class.getResource("Admin.fxml"));
        root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}