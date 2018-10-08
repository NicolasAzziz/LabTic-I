package grupo1.labtic.ui.restaurants;


import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.Usuario;
import grupo1.labtic.services.entities.restaurant.Mesa;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolicitarDatos {

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
    private Button mas;
    @FXML
    private TableView<Mesa> tabla;
    @FXML
    private TableColumn<Mesa,Integer> column1;
    @FXML
    private TableColumn<Mesa, Integer> column2;

    ObservableList<Mesa> mesas;




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

                        //
//                        List<CheckMenuItem> itemsComidas = comidasMenu.getItems();
                        List<String> selectedItemsComidas = comidasMenu.getItems().stream().filter(item ->
                                CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                                .map(MenuItem::getText).collect(Collectors.toList());

                        List<String> selectedItemsTipoDePagoMenu = metodosPagoMenu.getItems().stream().filter(item ->
                                CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                                .map(MenuItem::getText).collect(Collectors.toList());


                        service.setTipoDePagoList(login, selectedItemsTipoDePagoMenu);

                        service.setGrupoDeComidaList(login, selectedItemsComidas);

                        List<Mesa> mesaList = new ArrayList<>();
                        //---------------> AGREGAR LAS MESAS A ESTA LISTA <-------------------------------------------------

                        mesaList.add(new Mesa((Restaurant) restaurante,3,5));


                        service.setListaMesasRestaurante(login, mesaList);


                        service.registrarDatosRestaurant(id, nombre, telefono, direccion,barrio,habre,hcierra,descripcion,web,
                                nuevaPass);

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

    @FXML
    public void agregarMesa(ActionEvent actionEvent) {
        if(nMesas.getText()==null||nMesas.getText().equals("")||nSillas.getText()==null||nSillas.getText().equals("")){
            try{

                Integer numeroDeMesa = Integer.valueOf(nMesas.getText());
                Integer cantidadDeSillas = Integer.valueOf(nSillas.getText());

                Usuario u = repo.findOneByLogin(usuario.getText());
                long id = u.getId();
                Restaurant r = repo.findOneById(id);
                Mesa mesa = new Mesa();
                mesa.setNumeroReferencia(numeroDeMesa);
                mesa.setCantLugares(cantidadDeSillas);
                mesa.setRestaurant(r);
                mesas = FXCollections.observableArrayList();
                mesas.add(mesa);

               // column1.setCellValueFactory(new PropertyValueFactory("nroReferencia"));
                column2.setCellValueFactory(new PropertyValueFactory("cantLugares"));

               // tabla.setEditable(true);
                //tabla.getItems().
                tabla.getItems().addAll(new Mesa((Restaurant) u,numeroDeMesa,cantidadDeSillas));

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
