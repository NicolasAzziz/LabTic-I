package grupo1.labtic.ui.cliente;

import grupo1.labtic.services.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEspecifico {

    @Autowired
    private Principal principal;

    @FXML
    private Text nombre;

    @FXML
    private TextArea descripcion;

    @FXML
    private Button reservar;

    @FXML
    private ComboBox<?> horaReserva;

    @FXML
    private ComboBox<?> cantPersonasReserva;

    @FXML
    private DatePicker fechaReserva;

    private Restaurant resto;


    @FXML
    void reservar(ActionEvent event) {
        nombre.setText("");
    }

    @FXML
    void mesasMenu(ActionEvent event){

        ToggleGroup toggleGroup = new ToggleGroup();

        for(int i = 0; i < resto.getMesas().size(); i++){
            RadioMenuItem radioItem = new RadioMenuItem(resto.getMesas().get(i).toString());
            radioItem.setToggleGroup(toggleGroup);
        }

    }

    public void sendResto(Restaurant resto){
        this.resto = resto;
    }




}
