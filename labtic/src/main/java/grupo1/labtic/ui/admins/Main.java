package grupo1.labtic.ui.admins;

import grupo1.labtic.LabticApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main extends Application {


    private AnnotationConfigApplicationContext context;
    private Parent root;

    public void start(Stage primaryStage) throws Exception{
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LabticApplication.class);
//        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
//        primaryStage.setTitle("admin");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();

        context = new AnnotationConfigApplicationContext(Main.class);

        FXMLLoader loader = new FXMLLoader(Administrar.class.getResource("admin.fxml"));
        loader.setControllerFactory(context::getBean);

        root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
