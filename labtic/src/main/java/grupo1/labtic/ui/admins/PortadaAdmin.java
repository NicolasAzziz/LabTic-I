package grupo1.labtic.ui.admins;

import grupo1.labtic.AppApplication;
import grupo1.labtic.persistence.ReservaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Reserva;
import grupo1.labtic.services.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class PortadaAdmin {

    private long precioPorReserva = 10L;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Restaurant> table;

    @FXML
    private TableColumn<Restaurant, String> col_nombre;

    @FXML
    private TableColumn<Restaurant, String> col_email;

    @FXML
    private TableColumn<Restaurant, String> col_rut;

    @FXML
    private TableColumn<Restaurant, String> col_barrio;

    @FXML
    private TableColumn<Restaurant, String> col_telefono;

    @FXML
    private TableColumn<Restaurant, String> col_direccion;

    @FXML
    private Button agregarRestaurant;

    @FXML
    private Circle circle2;

    @FXML
    private AnchorPane imageContainer;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @FXML
    private Text nombre;
    @FXML
    private Text description;
    @FXML
    private Text barrioPM;
    @FXML
    private Text direccion;
    @FXML
    private Text tel;
    @FXML
    private Text horario;
    @FXML
    private HBox hBox;
    @FXML
    private ImageView logo;
    @FXML
    private Text comidas;
    @FXML
    private Text pagos;
    @FXML
    private DatePicker fechaHasta;
    @FXML
    private DatePicker fechaDesde;
    @FXML
    private Text importe;

    private ReservaRepository reservaRepository;

    private Restaurant rowData;



    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_nombre != null : "fx:id=\"col_nombre\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_email != null : "fx:id=\"col_email\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_rut != null : "fx:id=\"col_rut\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_barrio != null : "fx:id=\"col_barrio\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_telefono != null : "fx:id=\"col_telefono\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert col_direccion != null : "fx:id=\"col_direccion\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert agregarRestaurant != null : "fx:id=\"agregarRestaurant\" was not injected: check your FXML file 'adminPortada.fxml'.";
        assert circle2 != null : "fx:id=\"circle2\" was not injected: check your FXML file 'adminPortada.fxml'.";

        col_nombre.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("nombreRestaurant"));
        col_email.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("email"));
        col_rut.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("rut"));
        col_barrio.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("barrio"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("telefono"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("direccion"));

        Iterable<Restaurant> listaRestaurantes = restaurantRepository.findAll();
        ObservableList<Restaurant> data = FXCollections.observableList((List) listaRestaurantes);

        table.setItems(data);

        table.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked((event1) -> {
                if (event1.getClickCount() == 2 && (!row.isEmpty())) {
                    rowData = row.getItem();
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(AppApplication.getContext()::getBean);
                        Parent root = loader.load(PortadaAdmin.class.getResourceAsStream("restauranteEspecifico.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Restaurant específico");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        stage.setScene(new Scene(root));
                        nombre.setText(rowData.getNombreRestaurant());
                        description.setText(rowData.getDescripcion());
                        barrioPM.setText(rowData.getBarrio() + " - " + rowData.getPrecioMedio());
                        tel.setText(rowData.getTelefono());
                        direccion.setText(rowData.getDireccion());
                        horario.setText(rowData.getHorarioApertura() + " - " + rowData.getHorarioCierre());
                        logo.setImage(rowData.getImageView().getImage());
                        comidas.setText(rowData.getCocinasOfrecidasString());
                        pagos.setText(rowData.getTipoDePagoListString());
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });


//        imageView.setPreserveRatio(true);
//        imageView.fitWidthProperty().bind(imageContainer.widthProperty());
        //imageView.fitHeightProperty().bind(imageContainer.heightProperty());
    }

    @FXML
    void actualizarTabla(ActionEvent event) {
        Iterable<Restaurant> listaRestaurantes = restaurantRepository.findAll();
        ObservableList<Restaurant> data = FXCollections.observableList((List) listaRestaurantes);
        table.setItems(data);
    }

    @FXML
    public void agregarRestaurant(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(AppApplication.getContext()::getBean);
            Parent root = loader.load(Administrar.class.getResourceAsStream("nuevoRestaurant.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Nuevo Restaurant");
            stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
            double w = ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).getWidth();
            double h = ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).getHeight();
            stage.setScene(new Scene(root));
            stage.setHeight(h);
            stage.setWidth(w);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void facturar(ActionEvent event) {

        if(fechaDesde.getValue() == null || fechaHasta.getValue() == null){

            showAlert("Fechas invalidas!", "No se registraron fechas correctas para realizar la facturacion");

        }else {

            LocalDate dateDesde = fechaDesde.getValue();
            LocalDate dateHasta = fechaHasta.getValue();

            if (dateDesde.isAfter(LocalDate.now()) || dateHasta.isAfter(LocalDate.now()) || dateDesde.isAfter(dateHasta)){

                showAlert("Fechas invalidas!", "Las fechas registradas no son correctas");

            }else {



                List<Reserva> reservas = new ArrayList<>();

                Iterable<Reserva> reservasAceptadas = reservaRepository.getReservasByEstadoAndRestaurantAndFechaYhoraIsBetween("Aceptado", this.rowData, dateDesde, dateHasta);
                Iterable<Reserva> reservasFinzalizadas = reservaRepository.getReservasByEstadoAndRestaurantAndFechaYhoraIsBetween("Finalizado", this.rowData, dateDesde, dateHasta);

                reservasFinzalizadas.forEach(reserva -> reservas.add(reserva));
                reservasAceptadas.forEach(reserva -> reservas.add(reserva));

                long importeAPagar = reservas.size() * precioPorReserva;

                importe.setText("$ " + importeAPagar);
                
            }


        }

    }


}


