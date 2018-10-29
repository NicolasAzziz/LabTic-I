package grupo1.labtic.ui.restaurants;


import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.services.exceptions.NroReferenciaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolicitarDatos {

    @Autowired
    RestaurantService serviceRestaurant;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @FXML
    private TextField email;
    @FXML
    private TextField nombreRestaurante;
    @FXML
    private TextField telefonoRestaurante;
    @FXML
    private TextField direccionRestaurante;
    @FXML
    private TextField precioMedio;
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
    private TextField nMesas;
    @FXML
    private TextField nSillas;
    @FXML
    private Button mas;
    @FXML
    private ListView<Mesa> listMesas;

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
                mCierreRestaurante.getText() == null || mCierreRestaurante.getText().equals("") || email.getText() == null || email.getText().equals("")){

            showAlert("Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");
        } else {

                String email = this.email.getText();
                Restaurant restaurante = restaurantRepository.getRestaurantByEmail(email);

                if(restaurante == null){
                    showAlert("Usuario no encontrado", "El email: " + this.email.getText() +" no existe en el sistema");

                }else {

                    if (restaurante.getPassword().equals(passActual.getText())) {
                        try {
                            String nombre = nombreRestaurante.getText();
                            String telefono = (telefonoRestaurante.getText());
                            String direccion = direccionRestaurante.getText();
                            String barrio = null;
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
                                    CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                                    .map(MenuItem::getText).collect(Collectors.toList());
                            if (selectedBarrio.size() == 1) {
                                barrio = selectedBarrio.get(0);
                            } else {
                                showAlert("Informacion Invalida", "Se encontró un error al registrar el Barrio");
                            }

                            serviceRestaurant.setGrupoDeComidaList(restaurante, selectedItemsComidas);

                            List<Mesa> mesas = new ArrayList<>(mesaList);

                            serviceRestaurant.setListaMesasRestaurante(restaurante, mesas);

                            serviceRestaurant.registrarDatosRestaurant(restaurante, nombre, telefono, direccion, barrio, habre, hcierra, descripcion, web,
                                    nuevaPass);
                            if(imgFile != null) {
                                serviceRestaurant.guardarImagen(restaurante, imgFile);
                            }

                            if(precioMedio == null || precioMedio.getText().equals("")){

                            }else{
                                serviceRestaurant.setPrecioMedio(restaurante,precioMedio.getText());
                            }
                            showAlert("Datos guardados", "Se guardaron con éxito los datos de su restaurante");
                            clean();
                        } catch (NumberFormatException e) {
                            showAlert("Informacion Invalida", "Se encontró un error en los datos ingresados");
                        }
                    } else {
                        showAlert("Contraseña incorrecta", "La contraseña ingresada es incorrecta");
                    }
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


    }


    private void cleanMesas() {
        nMesas.setText(null);
        nSillas.setText(null);
        mesaList.clear();
        listMesas.setItems(mesaList);
    }

    private void clean() {
        cleanMesas();
        precioMedio.setText(null);
        email.setText(null);
        nombreRestaurante.setText(null);
        passActual.setText(null);
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

        for(int i = 0; i< barriosMenu.getItems().size();i++){
            ((CheckMenuItem)barriosMenu.getItems().get(i)).setSelected(false);
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