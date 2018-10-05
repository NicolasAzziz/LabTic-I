package grupo1.labtic.ui.restaurants;

import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.restaurant.Restaurant;
import grupo1.labtic.services.entities.restaurant.comida.*;
import grupo1.labtic.services.entities.restaurant.pago.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private CheckMenuItem cafeteria;
    @FXML
    private CheckMenuItem parrilla;
    @FXML
    private CheckMenuItem celiacos;
    @FXML
    private CheckMenuItem chivitos;
    @FXML
    private CheckMenuItem comidaChina;
    @FXML
    private CheckMenuItem comidaMexicana;
    @FXML
    private CheckMenuItem comidaVegetariana;
    @FXML
    private CheckMenuItem comidaVegana;
    @FXML
    private CheckMenuItem milanesas;
    @FXML
    private CheckMenuItem pescadoMariscos;
    @FXML
    private CheckMenuItem pizza;
    @FXML
    private CheckMenuItem sandwiches;
    @FXML
    private CheckMenuItem tartas;
    @FXML
    private CheckMenuItem wrap;
    @FXML
    private CheckMenuItem wok;
    @FXML
    private TextField nMesas;
    @FXML
    private  TextField nSillas;

    @FXML
    private ListView<String> data;

    @FXML
    public void registrar (javafx.event.ActionEvent event){
        if (nombreRestaurante.getText()==null||nombreRestaurante.getText().equals("")||telefonoRestaurante.getText()==null||
        telefonoRestaurante.getText().equals("")||direccionRestaurante.getText()==null||direccionRestaurante.getText().equals("")||
        barrioRestaurante.getText()==null||barrioRestaurante.getText().equals("")||hAperturaRestaurante.getText()==null||
        mAperturaRestaurante.getText()==null||mAperturaRestaurante.getText().equals("")||hCierreRestaurante.getText()==null||
        "".equals(hCierreRestaurante.getText())||mCierreRestaurante.getText()==null||mCierreRestaurante.getText().equals("")
        ||usuario.getText()==null||usuario.getText().equals("")){
            showAlert("Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");
        }
        else{
            try{
                String login = usuario.getText();
                Usuario restaurante =  repo.findOneByLogin(login);
                long id = restaurante.getId();
                if(restaurante.getPassword().equals(passActual.getText())){
                    try{
                        String nombre = nombreRestaurante.getText();
                        String telefono = (telefonoRestaurante.getText());
                        String direccion = direccionRestaurante.getText();
                        String barrio = barrioRestaurante.getText();
                        Integer hAbre = Integer.valueOf(hAperturaRestaurante.getText());
                        Integer mAbre = Integer.valueOf(mAperturaRestaurante.getText());
                        String habre = hAperturaRestaurante.getText()+ ":" + mAperturaRestaurante.getText();
                        Integer hCierra = Integer.valueOf(hCierreRestaurante.getText());
                        Integer mCierra = Integer.valueOf(mCierreRestaurante.getText());
                        String hcierra = hCierreRestaurante.getText() + ":" + mCierreRestaurante.getText();
                        String descripcion = descR.getText();
                        String web = webRestaurante.getText();
                        String nuevaPass = passNueva.getText();
                        int mesas = Integer.valueOf(nMesas.getText());
                        boolean setSushi = false;
                        boolean setHamburguesas=false;
                        boolean setEnsaladas= false;
                        boolean setCafe = false;
                        boolean setParrilla = false;
                        boolean setCeliacos = false;
                        boolean setChivitos = false;
                        boolean setChina = false;
                        boolean setMex = false;
                        boolean setVegetariana = false;
                        boolean setVegana = false;
                        boolean setMilanesas = false;
                        boolean setPM = false;
                        boolean setPizza = false;
                        boolean setSandwiches = false;
                        boolean setTartas = false;
                        boolean setWraps = false;
                        boolean setWok = false;


                        if(sushi.isSelected()){
                           setSushi = true;
                        }
                        if(hamburgesas.isSelected()){
                            setHamburguesas = true;
                        }
                        if(ensaladas.isSelected()){
                            setEnsaladas= true;
                        }
                        if(cafeteria.isSelected()){
                            setCafe = true;
                        }
                        if(parrilla.isSelected()){
                            setParrilla = true;
                        }
                        if(celiacos.isSelected()){
                            setCeliacos = true;
                        }
                        if(chivitos.isSelected()){
                            setChivitos = true;
                        }
                        if(comidaChina.isSelected()){
                            setChina = true;
                        }
                        if(comidaMexicana.isSelected()){
                            setMex = true;
                        }
                        if(comidaVegetariana.isSelected()){
                            setVegetariana = true;
                        }
                        if(comidaVegana.isSelected()){
                            setVegana = true;
                        }
                        if(milanesas.isSelected()){
                            setMilanesas= true;
                        }
                        if(pescadoMariscos.isSelected()){
                            setPM = true;
                        }
                        if(pizza.isSelected()){
                            setPizza = true;
                        }
                        if(sandwiches.isSelected()){
                            setSandwiches = true;
                        }
                        if(tartas.isSelected()){
                            setTartas= true;
                        }
                        if(wrap.isSelected()){
                            setWraps = true;
                        }
                        if(wok.isSelected()){

                            setWok = true;
                        }
                        Comida comida = new Comida(login, setSushi, setHamburguesas, setEnsaladas, setCafe, setParrilla, setCeliacos, setChivitos,
                                setChina, setMex, setVegetariana, setVegana, setMilanesas, setPM, setPizza, setSandwiches, setTartas,
                                setWraps, setWok);

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
                        service.registrarDatosRestaurant(id, nombre, telefono, direccion,barrio,habre,hcierra,descripcion,web,
                                nuevaPass,mesas,metodoDePagoList,cocinaList, comida);
                        showAlert("Datos guardados", "Se guardaron con éxito los datos de su restaurante");
                        clean();
                    }
                    catch(NumberFormatException e){
                        showAlert("Informacion Invalida", "Se encontró un error en los dtos ingresados");
                    }
                }
                else{
                    showAlert("Contraseña incorrecta", "La contraseña ingresada es incorrecta");
                }
            }
            catch (NullPointerException e){
                showAlert("usuario no encontrado", "El nombre de usuario ingresado no existe en el sistema");
            }

        }
    }

    public void agregarMesa(ActionEvent actionEvent) {
        ObservableList<String> datos = FXCollections.observableArrayList();
        if(nMesas.getText()==null||nMesas.getText().equals("")||nSillas.getText()==null||nSillas.getText().equals("")){
            try{
                Integer numeroDeMesa = Integer.valueOf(nMesas.getText());
                Integer cantidadDeSillas = Integer.valueOf(nSillas.getText());
                String agregar = nMesas.getText()+" -> "+nSillas.getText();
                datos.add(agregar);
                data.setItems(datos);
                cleanMesas();
            }
            catch (NumberFormatException e){
                showAlert("No se pudo agregar la mesa",
                        "El número o la cantidad de lugares disponibles en la mesa son inválidos");
            }
        }
    }

    public void cleanMesas(){
        nMesas.setText(null);
        nSillas.setText(null);
    }

    public void clean(){
        usuario.setText(null);
        nombreRestaurante.setText(null);
        telefonoRestaurante.setText(null);
        descR.setText(null);
        webRestaurante.setText(null);
        direccionRestaurante.setText(null);
        barrioRestaurante.setText(null);
        hAperturaRestaurante.setText(null);
        mAperturaRestaurante.setText(null);
        hCierreRestaurante.setText(null);
        mCierreRestaurante.setText(null);
        tarjetaD.setSelected(false);
        tarjetaC.setSelected(false);
        efectivo.setSelected(false);
        ticketA.setSelected(false);
        ticketR.setSelected(false);
        sushi.setSelected(false);
        hamburgesas.setSelected(false);
        ensaladas.setSelected(false);
    }

    public void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
