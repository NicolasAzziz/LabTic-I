package grupo1.labtic.ui.restaurants;

import com.jfoenix.controls.JFXButton;
import grupo1.labtic.AppApplication;
import grupo1.labtic.services.ReservaService;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.ui.LoginController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class RestaurantePrincipal {


    @Autowired
    private ReservaService reservaService;
    @Autowired
    private RestaurantService restaurantService;

    @FXML
    private JFXButton ajustes;
    @FXML
    private JFXButton facturacion;
    @FXML
    private JFXButton facturar;
    @FXML
    private DatePicker fechaHasta;
    @FXML
    private DatePicker fechaDesde;
    @FXML
    private Text importe;

    @FXML
    private TableView<Reserva> reservasPendientes;

    @FXML
    private TableColumn<Reserva, String> nombreClienteRP;

    @FXML
    private TableColumn<Reserva, Mesa> mesaRP;

    @FXML
    private TableColumn<Reserva, String> horaRP;

    @FXML
    private TableView<Reserva> reservasActivas;

    @FXML
    private TableColumn<Reserva, String> nombreClienteRA;

    @FXML
    private TableColumn<Reserva, Mesa> mesaRA;

    @FXML
    private TableColumn<Mesa, Integer> nroMesaRA;

    @FXML
    private TableColumn<Mesa, Integer> lugarMesaRA;

    @FXML
    private TableColumn<Reserva, String> horaRA;

    @FXML
    private TableColumn<Mesa, Integer> nroMesaRP;

    @FXML
    private TableColumn<Mesa, Integer> lugarMesaRP;

    @FXML
    private TableView<Mesa> mesasLibres;

    @FXML
    private TableColumn<Mesa, Integer> nroMesaML;

    @FXML
    private TableColumn<Mesa, Integer> lugarML;

    @FXML
    private TableView<Mesa> mesasOcupadas;

    @FXML
    private TableColumn<Mesa, Integer> nroMesaMO;

    @FXML
    private TableColumn<Mesa, Integer> lugarMO;

    @FXML
    private Label nombreRestaurant;

    private Reserva reserva;

    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        nombreRestaurant.setText(restaurant.getNombreRestaurant());
        reservasPendientes();
        reservasActivas();
        mesasLibres();
        mesasOcupadas();
    }

    @FXML
    void initialize() {
        reservasActivas.setRowFactory(tv -> {
            TableRow<Reserva> row = new TableRow<>();
            row.setOnMouseClicked((event1) -> {
                if (event1.getClickCount() == 2 && (!row.isEmpty())) {
                    reserva = row.getItem();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setControllerFactory(AppApplication.getContext()::getBean);
                    loader.setLocation(ReservasPendientes.class.getResource("reservasActivas.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    ReservasActivas reservasActivasController = loader.<ReservasActivas>getController();
                    reservasActivasController.setReserva(reserva);
                    reservasActivasController.restaurantPrincipal(this);
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
                    reservaPendientesController.setRestaurantePrincipal(this);
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


    @FXML
    void refresh(ActionEvent event) {
        actualizarTablas();
    }

    public void actualizarTablas() {
        reservasPendientes.setItems(FXCollections.observableList(reservaService.reservasPendientes()));
        reservasActivas.setItems(FXCollections.observableList(reservaService.reservasActivas()));
        List<Mesa> mesasLibresRest = restaurantService.mesasLibres(restaurant);
        mesasLibres.setItems(FXCollections.observableList(mesasLibresRest));
        List<Mesa> mesasOcupadasRest = restaurantService.mesasOcupadas(restaurant);
        mesasOcupadas.setItems(FXCollections.observableList(mesasOcupadasRest));
    }

    private void reservasPendientes() {

        nombreClienteRP.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombreCliente"));
        mesaRP.setCellValueFactory(new PropertyValueFactory<Reserva, Mesa>("mesa"));
        lugarMesaRP.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("cantLugares"));
        nroMesaRP.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("nroReferencia"));
        horaRP.setCellValueFactory(new PropertyValueFactory<Reserva, String>("horaString"));
        reservasPendientes.setItems(FXCollections.observableList(reservaService.reservasPendientes()));
    }

    private void reservasActivas() {
        nombreClienteRA.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombreCliente"));
        mesaRA.setCellValueFactory(new PropertyValueFactory<Reserva, Mesa>("mesa"));
        lugarMesaRA.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("cantLugares"));
        nroMesaRA.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("nroReferencia"));
        horaRA.setCellValueFactory(new PropertyValueFactory<Reserva, String>("horaString"));
        reservasActivas.setItems(FXCollections.observableList(reservaService.reservasActivas()));
    }


    private void mesasLibres() {
        nroMesaML.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("nroReferencia"));
        lugarML.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("cantLugares"));
        List<Mesa> mesasLibresRest = restaurantService.mesasLibres(restaurant);
        mesasLibres.setItems(FXCollections.observableList(mesasLibresRest));
    }

    private void mesasOcupadas() {
        nroMesaMO.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("nroReferencia"));
        lugarMO.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("cantLugares"));
        List<Mesa> mesasOcupadasRest = restaurantService.mesasOcupadas(restaurant);
//        if (mesasOcupadasRest.size() != 0)
        mesasOcupadas.setItems(FXCollections.observableList(mesasOcupadasRest));
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(AppApplication.getContext()::getBean);
        Parent root = null;
        try {
            root = loader.load(LoginController.class.getResourceAsStream("login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("¡Bienvenido!");
        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        double w = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getWidth();
        double h = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getHeight();
        stage.setScene(new Scene(root));
        stage.setHeight(h);
        stage.setWidth(w);
        stage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void ajustes(ActionEvent event) {
        //Cambiar datos
    }

    @FXML
    void facturacion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(AppApplication.getContext()::getBean);
        loader.setLocation(RestaurantePrincipal.class.getResource("facturacion.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        importe.setText("");
        Parent root = loader.getRoot();

        Stage stage = new Stage();

        stage.setTitle("Facturacion");

        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        double w = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getWidth();
        double h = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getHeight();
        stage.setScene(new Scene(root));
        stage.setHeight(h);
        stage.setWidth(w);
        stage.show();
    }

    @FXML
    void facturar(ActionEvent event) {

        if (fechaDesde.getValue() == null || fechaHasta.getValue() == null) {

            showAlert("Fechas invalidas!", "No se registraron fechas correctas para realizar la facturacion");

        } else {

            LocalDate dateDesde = fechaDesde.getValue();
            LocalDate dateHasta = fechaHasta.getValue();

            if (dateDesde.isAfter(LocalDate.now()) || dateHasta.isAfter(LocalDate.now()) || dateDesde.isAfter(dateHasta)) {

                showAlert("Fechas invalidas!", "Las fechas registradas no son correctas");

            } else {

                List<Reserva> reservas = new ArrayList<>();
                List<Reserva> reservasFiltradas = new ArrayList<>();

                Date formatDesde = java.sql.Date.valueOf(dateDesde);
                Date formatHasta = java.sql.Date.valueOf(dateHasta);

                Iterable<Reserva> reservasAceptadas = reservaService.getReservasByRestaurantAndEstadoAceptado(restaurant);
                Iterable<Reserva> reservasFinzalizadas = reservaService.getReservasByRestaurantAndEstadoFinalizado(restaurant);

                reservasAceptadas.forEach(reserva -> reservas.add(reserva));
                reservasFinzalizadas.forEach(reserva -> reservas.add(reserva));


                for (int i = 0; i < reservas.size(); i++) {

//  No esta comparando las fechas

                    if (reservas.get(i).getFechaYhora().after(formatDesde) && reservas.get(i).getFechaYhora().before(formatHasta)) {
                        reservasFiltradas.add(reservas.get(i));
                    }
                }
//CARGAR PRECIO
                long importeAPagar = reservasFiltradas.size() * 10L;
                System.out.println(importeAPagar);

                importe.setText("EL MONTO CORRESPONDIENTE A LA FECHA INDICADA  ES DE $ " + importeAPagar);

            }


        }

    }

}
