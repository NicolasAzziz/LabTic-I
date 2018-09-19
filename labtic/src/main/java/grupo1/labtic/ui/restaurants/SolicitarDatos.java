package grupo1.labtic.ui.restaurants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;


public class SolicitarDatos {
    @FXML
    private ChoiceBox<CheckMenuItem> metodosPago;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        loadData();
    }

    public void loadData(){
        list.removeAll(list);
        CheckMenuItem tarjetaD = new CheckMenuItem("Tarjeta de débito");
        CheckMenuItem tarjetaC = new CheckMenuItem("Tarjeta de crédito");
        CheckMenuItem efectivo = new CheckMenuItem("Efectivo");
        CheckMenuItem tAlimentacion = new CheckMenuItem("Ticket de alimentación");
        CheckMenuItem tRestaurante = new CheckMenuItem("Ticket restaurante");
        list.addAll(tarjetaC, tarjetaD, efectivo, tAlimentacion, tRestaurante);
        metodosPago.getItems().addAll(list);
    }

    public void desplegarMetodosDePago(MouseEvent mouseEvent) {
    }
}
