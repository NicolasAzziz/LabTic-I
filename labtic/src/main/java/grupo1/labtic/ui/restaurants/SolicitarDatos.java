package grupo1.labtic.ui.restaurants;


import grupo1.labtic.AdminApplication;
import grupo1.labtic.ClienteApplication;
import grupo1.labtic.RestaurantApplication;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.services.exceptions.NroReferenciaException;
import grupo1.labtic.ui.admins.PortadaAdmin;
import grupo1.labtic.ui.cliente.Principal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class SolicitarDatos {

    @Autowired
    RestaurantService serviceRestaurant;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @FXML
    private TextField nombreRestaurante;

    @FXML
    private PasswordField passNueva;

    @FXML
    private PasswordField passNuevaRepeat;

    @FXML
    private TextField direccionRestaurante;

    @FXML
    private MenuButton barriosMenu;

    @FXML
    private RadioMenuItem ciudadVieja;

    @FXML
    private RadioMenuItem centro;

    @FXML
    private RadioMenuItem barrioSur;

    @FXML
    private RadioMenuItem palermo;

    @FXML
    private RadioMenuItem puntaCarretas;

    @FXML
    private RadioMenuItem maronias;

    @FXML
    private RadioMenuItem cordon;

    @FXML
    private RadioMenuItem buceo;

    @FXML
    private RadioMenuItem malvin;

    @FXML
    private RadioMenuItem pocitos;

    @FXML
    private RadioMenuItem parqueBatlle;

    @FXML
    private RadioMenuItem puntaGorda;

    @FXML
    private RadioMenuItem carrasco;

    @FXML
    private TextField hAperturaRestaurante;

    @FXML
    private TextField mAperturaRestaurante;

    @FXML
    private TextField hCierreRestaurante;

    @FXML
    private TextField mCierreRestaurante;

    @FXML
    private MenuButton metodosPagoMenu;

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
    private MenuButton comidasMenu;

    @FXML
    private CheckMenuItem sushi;

    @FXML
    private CheckMenuItem hamburgesas;

    @FXML
    private CheckMenuItem ensaladas;

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
    private TextField telefonoRestaurante;

    @FXML
    private TextArea descR;

    @FXML
    private ListView<Mesa> listMesas;

    @FXML
    private TextField webRestaurante;

    @FXML
    private TextField precioMedio;

    @FXML
    private ImageView imagenVistaPrevia;

    @FXML
    private TextField nMesas;

    @FXML
    private TextField nSillas;

    @FXML
    private GridPane imgContainer;

    private File imgFile;

    ObservableList<Mesa> mesaList;

    @FXML
    public void agregarMesa(ActionEvent actionEvent) {
        String n = nMesas.getText();
        String g = nSillas.getText().toString();

        if (!(nMesas.getText() == null || nMesas.getText().equals("") || nSillas.getText() == null && nSillas.getText().equals(""))) {
            try {
                mesaList = mesaList == null ? FXCollections.<Mesa>observableArrayList() : mesaList;

                Integer referencia = Integer.valueOf(nMesas.getText());
                Integer capacidad = Integer.valueOf(nSillas.getText());
                Mesa mesa = new Mesa( referencia, capacidad);

                verificacionMesas(mesa);

                mesaList.add(mesa);

                listMesas.setItems(mesaList);
                cleanMesas();

            } catch (NumberFormatException e) {
                showAlert("No se pudo agregar la mesa",
                        "El número o la cantidad de lugares disponibles en la mesa son inválidos");
            } catch (NroReferenciaException e){
                showAlert("No se puedo agregar la mesa",
                        e.getMessage());
            }
        }
    }
    private boolean verificacionMesas(Mesa mesa) throws NroReferenciaException {
        boolean bExit = false;
        if(mesaList == null || mesaList.size() == 0){
            bExit = true;
        }else{
            for(int i = 0; i<mesaList.size(); i++){
                if(mesa.getNumeroReferencia() == mesaList.get(i).getNumeroReferencia())
                    throw new NroReferenciaException("Ya agregó una mesa con ese numero de referencia.");
            }
        }
        return bExit;
    }
    @FXML
    public void registrar(ActionEvent event) {
        if (nombreRestaurante.getText() == null || nombreRestaurante.getText().equals("") || telefonoRestaurante.getText() == null ||
                telefonoRestaurante.getText().equals("") || direccionRestaurante.getText() == null || direccionRestaurante.getText().equals("") ||
                hAperturaRestaurante.getText() == null || hAperturaRestaurante.getText().equals("") || mAperturaRestaurante.getText() == null ||
                mAperturaRestaurante.getText().equals("") || hCierreRestaurante.getText() == null || hCierreRestaurante.getText().equals("") ||
                mCierreRestaurante.getText() == null || mCierreRestaurante.getText().equals("") ||  barriosMenu.getItems().stream().filter(item ->
                RadioMenuItem.class.isInstance(item) && RadioMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText).collect(Collectors.toList()).size() == 0 ){

            showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");
        } else {
                Restaurant restaurante = Inicio.getRestaurant();

                if (passNueva.getText().equals(passNuevaRepeat.getText())) {
                        try {
                            String nombre = nombreRestaurante.getText();
                            String telefono = (telefonoRestaurante.getText());
                            String direccion = direccionRestaurante.getText();
                            Integer hAbre = Integer.valueOf(hAperturaRestaurante.getText());
                            Integer mAbre = Integer.valueOf(mAperturaRestaurante.getText());
                            String habre = hAperturaRestaurante.getText() + ":" + mAperturaRestaurante.getText();
                            Integer hCierra = Integer.valueOf(hCierreRestaurante.getText());
                            Integer mCierra = Integer.valueOf(mCierreRestaurante.getText());
                            String hcierra = hCierreRestaurante.getText() + ":" + mCierreRestaurante.getText();
                            String descripcion = descR.getText();
                            String web = webRestaurante.getText();
                            String nuevaPass = passNueva.getText();

//                        List<CheckMenuItem> itemsComidas = comidasMenu.getItems();

                            List<String> selectedItemsComidas = comidasMenu.getItems().stream().filter(item ->
                                    CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                                    .map(MenuItem::getText).collect(Collectors.toList());

                            List<String> selectedItemsTipoDePagoMenu = metodosPagoMenu.getItems().stream().filter(item ->
                                    CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                                    .map(MenuItem::getText).collect(Collectors.toList());

                        serviceRestaurant.setTipoDePagoList(restaurante, selectedItemsTipoDePagoMenu);

                        List<String> selectedBarrio = barriosMenu.getItems().stream().filter(item ->
                                RadioMenuItem.class.isInstance(item) && RadioMenuItem.class.cast(item).isSelected())
                                .map(MenuItem::getText).collect(Collectors.toList());

                        String barrio = selectedBarrio.get(0);

                        List<Mesa> mesas = new ArrayList<>(mesaList);

                        serviceRestaurant.setListaMesasRestaurante(restaurante, mesas);

                        serviceRestaurant.registrarDatosRestaurant(restaurante, nombre, telefono, direccion, barrio, habre, hcierra, descripcion, web, nuevaPass);

                        if(imgFile != null) {
                            serviceRestaurant.guardarImagen(restaurante, imgFile);
                        }

                        if(precioMedio == null || precioMedio.getText().equals("")){


                        }else{
                            serviceRestaurant.setPrecioMedio(restaurante,precioMedio.getText());
                        }
                            showAlert("Datos guardados", "Se guardaron con éxito los datos de su restaurante");
                            clean();

                            FXMLLoader loader = new FXMLLoader();
                            loader.setControllerFactory(RestaurantApplication.getContext()::getBean);
                            Parent root = loader.load(RestaurantePrincipal.class.getResourceAsStream("restaurantePrincipal.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("¡Bienvenido!");
                            stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                            stage.setScene(new Scene(root));
                            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();


                        } catch (NumberFormatException e) {
                            showAlert("Informacion Invalida", "Se encontró un error en los datos ingresados");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        showAlert("Contraseñas incorrectas", "Las contraseñas no coinciden.");
                    }
                }
        }


    @FXML
    public void selectedBarrio(ActionEvent actionEvent) {

        ToggleGroup toggleGroup = new ToggleGroup();

        ciudadVieja.setToggleGroup(toggleGroup);
        centro.setToggleGroup(toggleGroup);
        barrioSur.setToggleGroup(toggleGroup);
        palermo.setToggleGroup(toggleGroup);
        puntaCarretas.setToggleGroup(toggleGroup);
        maronias.setToggleGroup(toggleGroup);
        cordon.setToggleGroup(toggleGroup);
        buceo.setToggleGroup(toggleGroup);
        malvin.setToggleGroup(toggleGroup);
        pocitos.setToggleGroup(toggleGroup);
        parqueBatlle.setToggleGroup(toggleGroup);
        puntaGorda.setToggleGroup(toggleGroup);
        carrasco.setToggleGroup(toggleGroup);
    }

    public void cargarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        imgFile = fileChooser.showOpenDialog(null);

        imagenVistaPrevia.setImage((new javafx.scene.image.Image(imgFile.toURI().toString())));

        imagenVistaPrevia.fitWidthProperty().bind(imgContainer.widthProperty());
        imagenVistaPrevia.fitHeightProperty().bind(imgContainer.heightProperty());

    }


    private void cleanMesas() {
        nMesas.setText(null);
        nSillas.setText(null);
    }

    private void clean() {
        cleanMesas();
        precioMedio.setText(null);
        nombreRestaurante.setText(null);
        passNuevaRepeat.setText(null);
        passNueva.setText(null);
        telefonoRestaurante.setText(null);
        descR.setText(null);
        webRestaurante.setText(null);
        direccionRestaurante.setText(null);
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
        imagenVistaPrevia.setImage(null);
        for(int i = 0; i< barriosMenu.getItems().size();i++){
            ((RadioMenuItem)barriosMenu.getItems().get(i)).setSelected(false);
        }
    }




}