package grupo1.labtic.ui.restaurants;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

public class solicitarDatos {
    public void desplegarMetodosDePago(MouseEvent mouseEvent) {
        ChoiceBox metodosPago = (ChoiceBox) mouseEvent.getSource();
        metodosPago.getItems().add("tarjeta");
        metodosPago.getItems().add("Tarjeta de débito");
        metodosPago.getItems().add("Efectivo");
        metodosPago.getItems().add("Ticket de alimentación");
        metodosPago.getItems().add("Ticket Restaurante");
    }
}
