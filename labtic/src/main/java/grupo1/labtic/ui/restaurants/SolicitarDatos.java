package grupo1.labtic.ui.restaurants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class SolicitarDatos {
    @FXML
    private ChoiceBox metodosPago;

    ObservableList listaMetodosPago = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        loadData();
    }

    public void loadData(){
        listaMetodosPago.removeAll(listaMetodosPago);
        CheckMenuItem tarjetaD = new CheckMenuItem("Tarjeta de débito");
        CheckMenuItem tarjetaC = new CheckMenuItem("Tarjeta de crédito");
        CheckMenuItem efectivo = new CheckMenuItem("Efectivo");
        CheckMenuItem tAlimentacion = new CheckMenuItem("Ticket de alimentación");
        CheckMenuItem tRestaurante = new CheckMenuItem("Ticket restaurante");
        listaMetodosPago.addAll(tarjetaD, tarjetaC, efectivo, tAlimentacion, tRestaurante);
        metodosPago.getItems().addAll(listaMetodosPago);
    }

    public void desplegarMetodosDePago(MouseEvent mouseEvent) {
    }
}
