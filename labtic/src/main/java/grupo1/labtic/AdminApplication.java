package grupo1.labtic;

import grupo1.labtic.ui.admins.Administrar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
public class AdminApplication extends Application {


    private AnnotationConfigApplicationContext context;
    private Parent root;

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(AdminApplication.class, args);
//    }


    public void start(Stage primaryStage) {
//        SpringApplication.run(AdminApplication.class);
        context = new AnnotationConfigApplicationContext(AdminApplication.class);

        FXMLLoader loader = new FXMLLoader(Administrar.class.getResource("admin.fxml"));
        loader.setControllerFactory(context::getBean);
        try {
            root = loader.load();
        }catch (IOException e){
            e.getStackTrace();
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    //public static void main(String[] args) {
     //   launch(args);
    //}
}
