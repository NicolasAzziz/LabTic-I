package grupo1.labtic.ui.cliente;

import com.jfoenix.controls.JFXButton;
import grupo1.labtic.AppApplication;
import grupo1.labtic.services.ReservaService;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Reserva;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class VerReservas {

    @FXML
    private Text nombre;

    @FXML
    private JFXButton cerrarSesion;

    @FXML
    private TableView<Reserva> reservas;

    @FXML
    private TableColumn<Reserva, String> restaurante;

    @FXML
    private TableColumn<Reserva, Date> fecha;

    @FXML
    private TableColumn<Reserva, String> estado;

    @FXML
    private RadioMenuItem todas;
    @FXML
    private RadioMenuItem solicitadas;

    @FXML
    private MenuButton filtro;


    @Autowired
    private ReservaService reservaService;

    private Cliente cliente;

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        restaurante.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombreRestaurant"));
        fecha.setCellValueFactory(new PropertyValueFactory<Reserva, Date>("fechaYhora"));
        estado.setCellValueFactory(new PropertyValueFactory<Reserva, String>("estado"));
        List<Reserva> reservasByCliente = reservaService.getReservasByCliente(cliente);
        reservas.setItems(FXCollections.observableList(reservasByCliente));
    }


    ;


    @FXML
    public void filtro(ActionEvent actionEvent) {

        ToggleGroup toggleGroup = new ToggleGroup();

        todas.setToggleGroup(toggleGroup);
        solicitadas.setToggleGroup(toggleGroup);


       filtro.setText(filtroSeleccionado().getText());

       refreshTabla();
    }

    private RadioMenuItem filtroSeleccionado(){
        RadioMenuItem oExit = null;
        List<String> filtroSelected = filtro.getItems().stream().filter(item ->
                RadioMenuItem.class.isInstance(item) && RadioMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText).collect(Collectors.toList());
        if( filtroSelected.get(0).equals("Todas")){
            oExit = todas;
        }else if(filtroSelected.get(0).equals("Solicitadas")){
            oExit = solicitadas;
        }
        return oExit;
    }
    @FXML
    void atras(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void reservaInformacion(ActionEvent event) {
        Reserva reservaSeleccionada = reservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(AppApplication.getContext()::getBean);
            Parent root = null;
            try {
                root = loader.load(ReservaEspecifica.class.getResourceAsStream("ReservaEspecifica.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Reserva");
            stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
            double w = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getWidth();
            double h = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getHeight();
            stage.setScene(new Scene(root));
            stage.setHeight(h);
            stage.setWidth(w);
            ReservaEspecifica reservaEspecifica = loader.<ReservaEspecifica>getController();
            reservaEspecifica.setReserva(reservaSeleccionada);
            reservaEspecifica.setVerReservas(this);
            stage.show();
        } else {
            showAlert("Reserva", "Selecciona una reserva.");
        }
    }

    @FXML
    public void actualizarReservas(ActionEvent event) {
        refreshTabla();
    }

    public void refreshTabla() {
        List<Reserva> reservasByCliente = new ArrayList<>();
        if(filtroSeleccionado() == todas) {
             reservasByCliente = reservaService.getReservasByCliente(cliente);

        }else if( filtroSeleccionado() == solicitadas){
            reservasByCliente = reservaService.getReservasByClienteAndEstadoSolicitado(cliente);
        }
        if (reservasByCliente.size() != 0)
            reservas.setItems(FXCollections.observableList(reservasByCliente));
    }
}
