package grupo1.labtic.ui.admins;

import grupo1.labtic.services.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class RestauranteEspecifico {

    @Autowired
    private PortadaAdmin portadaAdmin;

    @FXML
    private DatePicker fechaHasta;

    @FXML
    private Text horario;

    @FXML
    private DatePicker fechaDesde;

    @FXML
    private Button facturar;

    @FXML
    private Text direccion;

    @FXML
    private Text description;

    @FXML
    private Text comidas;

    @FXML
    private Text nombre;

    @FXML
    private Text barrioPM;

    @FXML
    private Text importe;

    @FXML
    private Text pagos;

    @FXML
    private HBox hBox;

    @FXML
    private Text rate;

    @FXML
    private ImageView logo;

    @FXML
    private Text tel;

    private Restaurant resto;


    @FXML
    void facturar(ActionEvent event) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDateDesde = sdf.format(fechaDesde);
        String formatedDateHasta = sdf.format(fechaHasta);
        Date dateDesde =  null;
        Date dateHasta = null;
        try{
            dateDesde = sdf.parse(formatedDateDesde);
            dateHasta = sdf.parse(formatedDateHasta);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        if(fechaDesde.getValue() == null || fechaHasta.getValue() == null){

            showAlert("Fechas invalidas!", "No se registraron fechas correctas para realizar la facturacion");

        }else if (dateDesde.before(new Date()) || dateHasta.before(new Date()) || dateDesde.after(dateHasta)){

            showAlert("Fechas invalidas!", "Las fechas registradas no son correctas");
        }else {
            
        }

    }

    public void sendResto(Restaurant resto){
        this.resto = resto;
    }



}
