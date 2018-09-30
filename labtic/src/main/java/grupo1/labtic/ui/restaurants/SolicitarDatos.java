package grupo1.labtic.ui.restaurants;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.restaurant.Restaurant;
import grupo1.labtic.services.entities.restaurant.comida.Cocina;
import grupo1.labtic.services.entities.restaurant.comida.Ensaladas;
import grupo1.labtic.services.entities.restaurant.comida.Hamburgesas;
import grupo1.labtic.services.entities.restaurant.comida.Sushi;
import grupo1.labtic.services.entities.restaurant.pago.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SolicitarDatos {
    @Autowired
    private RestaurantRepository repo;
    @Autowired
    RestaurantService service;
    @FXML
    private TextField usuario;
    @FXML
    private TextField nombreRestaurante;
    @FXML
    private TextField telefonoRestaurante;
    @FXML
    private TextField direccionRestaurante;
    @FXML
    private TextField barrioRestaurante;
    @FXML
    private TextField hAperturaRestaurante;
    @FXML
    private TextField mAperturaRestaurante;
    @FXML
    private TextField hCierreRestaurante;
    @FXML
    private TextField mCierreRestaurante;
    @FXML
    private TextArea descR;
    @FXML
    private TextField webRestaurante;
    @FXML
    private TextField nMesasRestaurante;
    @FXML
    private MenuButton metodosPagoMenu;
    @FXML
    private MenuButton comidasMenu;
    @FXML
    private CheckMenuItem tarjetaD;
    @FXML
    private CheckMenuItem tarjetaC;
    @FXML
    private CheckMenuItem efectivo;
    @FXML
    private CheckMenuItem ticketA;
    @FXML
    private CheckMenuItem ticketR;
    @FXML
    private CheckMenuItem sushi;
    @FXML
    private CheckMenuItem hamburgesas;
    @FXML
    private CheckMenuItem ensaladas;
    @FXML
    private PasswordField passActual;
    @FXML
    private PasswordField passNueva;

    @FXML
    public void registrar (javafx.event.ActionEvent event){
        if (nombreRestaurante.getText()==null||nombreRestaurante.getText().equals("")||telefonoRestaurante.getText()==null||
        telefonoRestaurante.getText().equals("")||direccionRestaurante.getText()==null||direccionRestaurante.getText().equals("")||
        barrioRestaurante.getText()==null||barrioRestaurante.getText().equals("")||hAperturaRestaurante.getText()==null||
        mAperturaRestaurante.getText()==null||mAperturaRestaurante.getText().equals("")||hCierreRestaurante.getText()==null||
        "".equals(hCierreRestaurante.getText())||mCierreRestaurante.getText()==null||mCierreRestaurante.getText().equals("")||
        nMesasRestaurante.getText()==null||nMesasRestaurante.getText().equals("")||usuario.getText()==null||usuario.getText().equals("")){
            showAlert("Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");
        }
        else{
            try{
                Usuario restaurante =  repo.findOneByLogin(usuario.getText());
                long id = restaurante.getId();
                Restaurant resto = repo.findOneById(id);
                if(restaurante.getPassword().equals(passActual.getText())){
                    try{
                        String nombre = nombreRestaurante.getText();
                        String telefono = (telefonoRestaurante.getText());
                        String direccion = direccionRestaurante.getText();
                        String barrio = barrioRestaurante.getText();
                        Integer hAbre = Integer.valueOf(hAperturaRestaurante.getText());
                        Integer mAbre = Integer.valueOf(mAperturaRestaurante.getText());
                        String habre = hAbre.toString() + ":" + mAbre.toString();
                        Integer hCierra = Integer.valueOf(hCierreRestaurante.getText());
                        Integer mCierra = Integer.valueOf(mCierreRestaurante.getText());
                        String hcierra = hCierra.toString() + ":" + mCierra.toString();
                        String descripcion = descR.getText();
                        String web = webRestaurante.getText();
                        String nuevaPass = passNueva.getText();
                        int nMesas = Integer.valueOf(nMesasRestaurante.getText());

                        List<MetodoDePago> metodoDePagoList = new ArrayList<>();
                        if(tarjetaD.isSelected()){
                            metodoDePagoList.add(new TarjetaDebito());
                        }
                        if(tarjetaC.isSelected()){
                            metodoDePagoList.add(new TarjetaCredito());
                        }
                        if(ticketA.isSelected()){
                            metodoDePagoList.add(new TicketAlimentacion());
                        }
                        if(ticketR.isSelected()){
                            metodoDePagoList.add(new TicketRestaurant());
                        }
                        if(efectivo.isSelected()){
                            metodoDePagoList.add(new Efectivo());
                        }

                        List<Cocina> cocinaList = new ArrayList<>();
                        if(sushi.isSelected()){
                            cocinaList.add(new Sushi());
                        }
                        if(hamburgesas.isSelected()){
                            cocinaList.add(new Hamburgesas());
                        }
                        if(ensaladas.isSelected()){
                            cocinaList.add(new Ensaladas());
                        }

                        service.registrarDatosRestaurant(resto, nombre, telefono, direccion,barrio,habre,hcierra,descripcion,web,nuevaPass,nMesas,metodoDePagoList,cocinaList);
//                        long id = restaurante.getMetodoDePagoPK();
//                        Restaurant resto = repo.findOneById(id);


                    }
                    catch(NumberFormatException e){
                        showAlert("Informacion Invalida", "Se encontró un error en los dtos ingresados");
                    }
                }
                else{
                    showAlert("Contraseña incorrecta", "La contraseña ingresada es incorrecta");
                }
            }
            catch (Exception e){
                showAlert("usuario no encontrado", "El nombre de usuario ingresado no existe en el sistema");
            }

        }
    }

    public void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
