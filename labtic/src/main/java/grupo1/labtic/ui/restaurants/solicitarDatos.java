package grupo1.labtic.ui.restaurants;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

public class solicitarDatos {
    public void desplegarMetodosDePago(MouseEvent mouseEvent) {
        ChoiceBox metodosPago = (ChoiceBox) mouseEvent.getSource();
        metodosPago.getItems().add(new CheckMenuItem("Tarjeta de crédito"));
        metodosPago.getItems().add(new CheckMenuItem("Tarjeta de débito"));
        metodosPago.getItems().add(new CheckMenuItem("Efectivo"));
        metodosPago.getItems().add(new CheckMenuItem("Ticket de alimentación"));
        metodosPago.getItems().add(new CheckMenuItem("Ticket Restaurante"));
    }
}
