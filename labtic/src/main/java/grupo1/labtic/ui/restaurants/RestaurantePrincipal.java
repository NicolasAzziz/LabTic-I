package grupo1.labtic.ui.restaurants;

import com.jfoenix.controls.JFXButton;
import grupo1.labtic.AppApplication;
import grupo1.labtic.services.ReservaService;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.ui.LoginController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class RestaurantePrincipal {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @Autowired
    private ReservaService reservaService;

    @FXML
    private JFXButton ajustes;

    @FXML
    private TableView<Reserva> reservasPendientes;

    @FXML
    private TableColumn<Reserva, String> nomreClienteRP;

    @FXML
    private TableColumn<Reserva, Mesa> mesaRP;

    @FXML
    private TableColumn<Reserva, Date> horaRP;

    @FXML
    private TableView<Reserva> reservasActivas;

    @FXML
    private TableColumn<Reserva, String> nombreClienteRA;

    @FXML
    private TableColumn<Reserva, String> mesaRA;

    @FXML
    private TableColumn<Reserva, String> horaRA;

    @FXML
    private TableColumn<Mesa, Integer> nroMesaRP;

    @FXML
    private TableColumn<Reserva, Integer> lugaresMesaRP;

    @FXML
    private TableView<Mesa> mesasLibres;

    @FXML
    private TableColumn<Mesa, Integer> nroMesaML;

    @FXML
    private TableColumn<Mesa, Integer> lugaresML;

    @FXML
    private TableView<Mesa> mesasOcupadas;

    @FXML
    private TableColumn<Mesa, Integer> nroMesaMO;

    @FXML
    private TableColumn<Mesa, Integer> lugaresMO;

    private Reserva reserva;

    @FXML
    void initialize() {
        assert ajustes != null : "fx:id=\"ajustes\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert reservasPendientes != null : "fx:id=\"reservasPendientes\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert nomreClienteRP != null : "fx:id=\"nomreClienteRP\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert mesaRP != null : "fx:id=\"mesaRP\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert horaRP != null : "fx:id=\"horaRP\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert reservasActivas != null : "fx:id=\"reservasActivas\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert nombreClienteRA != null : "fx:id=\"nombreClienteRA\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert mesaRA != null : "fx:id=\"mesaRA\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert horaRA != null : "fx:id=\"horaRA\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert mesasLibres != null : "fx:id=\"mesasLibres\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert nroMesaML != null : "fx:id=\"nroMesaML\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert lugaresML != null : "fx:id=\"lugaresML\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert mesasOcupadas != null : "fx:id=\"mesasOcupadas\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert nroMesaMO != null : "fx:id=\"nroMesaMO\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        assert lugaresMO != null : "fx:id=\"lugaresMO\" was not injected: check your FXML file 'principalRestaurante.fxml'.";
        reservasPendientes();

        reservasPendientes.setRowFactory(tv -> {
            TableRow<Reserva> row = new TableRow<>();
            row.setOnMouseClicked((event1) -> {
                if (event1.getClickCount() == 2 && (!row.isEmpty())) {
                    reserva = row.getItem();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setControllerFactory(AppApplication.getContext()::getBean);
                    loader.setLocation(ReservasPendientes.class.getResource("reservasPendientes.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    ReservasPendientes reservaPendientesController = loader.<ReservasPendientes>getController();
                    reservaPendientesController.setReserva(reserva);
                    Stage stage = new Stage();
                    stage.setTitle("Reserva");
                    stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                    double w = ((Stage) ((Node) event1.getSource()).getScene().getWindow()).getWidth();
                    double h = ((Stage) ((Node) event1.getSource()).getScene().getWindow()).getHeight();
                    stage.setScene(new Scene(root));
                    stage.setHeight(h);
                    stage.setWidth(w);
                    stage.show();


                }
            });
            return row;
        });
    }

    private void reservasPendientes() {
        nomreClienteRP.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombreCliente"));
        mesaRP.setCellValueFactory(new PropertyValueFactory<Reserva, Mesa>("mesa"));
        lugaresMesaRP.setCellValueFactory(new PropertyValueFactory<Reserva, Integer>("cantLugares"));
        nroMesaRP.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("nroReferencia"));
        horaRP.setCellValueFactory(new PropertyValueFactory<Reserva, Date>("fechaYhora"));

        reservaService.reservasPendientes().get(0).getMesa().getCantLugares();
        reservaService.reservasPendientes().get(0).getNroReferencia();
        reservaService.reservasPendientes().get(0).getCantLugares();


        reservasPendientes.setItems(FXCollections.observableList(reservaService.reservasPendientes()));
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
//        ConfigurableApplicationContext context = SpringApplication.run(AppApplication.class);
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(context::getBean);
//
//        fxmlLoader.setLocation(LoginController.class.getResource("login.fxml"));
//
//        Parent root = null;
//        try {
//            root = fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.setTitle("Bienvenido!");
//        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
//        stage.show();
//
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }


}
