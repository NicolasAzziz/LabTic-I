package grupo1.labtic.ui.cliente;

import com.jfoenix.controls.JFXButton;
import grupo1.labtic.AppApplication;
import grupo1.labtic.persistence.GrupoDeComidaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.ReservaService;
import grupo1.labtic.services.RestaurantService;
import grupo1.labtic.services.entities.Cliente;
import grupo1.labtic.services.entities.Restaurant;
import grupo1.labtic.services.exceptions.ClienteSinReservas;
import grupo1.labtic.services.exceptions.ReservaYaSolicitada;
import grupo1.labtic.ui.LoginController;
import grupo1.labtic.services.entities.restaurant.Mesa;
import grupo1.labtic.ui.restaurants.RestaurantePrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static grupo1.labtic.ui.Alert.showAlert;

@Component
public class Principal {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private GrupoDeComidaRepository grupoDeComidaRepository;

    @Autowired
    private ReservaService reservaService;

    @FXML
    private MenuButton zonasToFindTable;
    @FXML
    private MenuButton comidasToFindTable;

    @FXML
    private TextField restaurantToFind;


    @FXML
    private TableView<Restaurant> tableViewRestaurantes;
    @FXML
    private TableColumn imagen;
    @FXML
    private TableColumn nombreRestaurante;
    @FXML
    private TableColumn rating;
    @FXML
    private TableColumn descripcion;
    @FXML
    private TableColumn barrio;
    @FXML
    private TableColumn cocinas;
    @FXML
    private JFXButton buscar;
    @FXML
    private JFXButton cerrarSesion;
    @FXML
    private JFXButton reservas;
    @FXML
    private JFXButton ajustes;

    private String mailResto;

    @FXML
    private Text raiting;

    @FXML
    private Text horario;

    @FXML
    private Text nombre;

    @FXML
    private Text description;

    @FXML
    private Text comidas;

    @FXML
    private Text pagos;

    @FXML
    private Button reservar;

    @FXML
    private Text precioMedio;

    @FXML
    private Text barrioRE;

    @FXML
    private Text direccion;

    @FXML
    private Text tel;

    @FXML
    private ImageView imagenRE;

    @FXML
    private HBox imagenContainer;

    @FXML
    private TableColumn numeroMesa;

    @FXML
    private TableColumn sillas;

    @FXML
    private TableView<Mesa> mesas;

    @FXML
    private TableColumn reservasColumn;

    private Cliente cliente;

    private Restaurant restaurant;

    @Autowired
    private RestaurantService restaurantService;

    private FXMLLoader fxmlLoader;

    private Parent root;

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    @FXML
    void initialize() {

        nombreRestaurante.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("nombreRestaurant"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("descripcion"));
        barrio.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("barrio"));
        imagen.setCellValueFactory(new PropertyValueFactory<Restaurant, ImageView>("imageView"));
        cocinas.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("cocinasOfrecidasString"));

        List<Restaurant> restaurantes = (List) repository.findAll();
        tableViewRestaurantes.setItems(FXCollections.observableList(restaurantes));


        tableViewRestaurantes.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked((event1) -> {
                if (event1.getClickCount() == 2 && (!row.isEmpty())) {
                    restaurant = row.getItem();
                    try {
                        loadRestaurantEspecifico(event1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }


    @FXML
    public void findTable(ActionEvent event) {

        List<String> selectedItemsComidas = comidasToFindTable.getItems().stream().filter(item ->
                CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText).collect(Collectors.toList());

        List<String> selectedBarrios = zonasToFindTable.getItems().stream().filter(item ->
                CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText).collect(Collectors.toList());

        if (restaurantToFind.getText() == null || restaurantToFind.getText().equals("")) {
            if (selectedBarrios.isEmpty() == true && selectedItemsComidas.isEmpty() == true) {
                initialize();

            } else if (selectedBarrios.isEmpty() == true && selectedItemsComidas.isEmpty() == false) {

                List<Restaurant> restaurantes = new ArrayList<>();

                if (selectedItemsComidas.size() == 1) {
                    Iterable<Restaurant> restos = repository.findAllByGrupoDeComidaList(grupoDeComidaRepository.getGrupoDeComidaByGrupo(selectedItemsComidas));
                    restos.forEach(resto -> restaurantes.add(resto));
                } else {

                    List<Restaurant> byComida = new ArrayList<>();

                    for (int i = 0; i < selectedItemsComidas.size(); i++) {

                        List<String> restoIndex = new ArrayList<>();

                        restoIndex.add(selectedItemsComidas.get(i));

                        Iterable<Restaurant> restos = repository.findAllByGrupoDeComidaList(grupoDeComidaRepository.getGrupoDeComidaByGrupo(restoIndex));

                        restos.forEach(resto -> restaurantes.add(resto));

                    }

                }
                restaurantesFiltrados(restaurantes);


            } else if (selectedBarrios.isEmpty() == false && selectedItemsComidas.isEmpty() == true) {

                List<Restaurant> restaurantes = new ArrayList<>();

                if (selectedBarrios.size() == 1) {
                    Iterable<Restaurant> restos = repository.findAllByBarrio(selectedBarrios);
                    restos.forEach(resto -> restaurantes.add(resto));
                } else {
                    for (int i = 0; i < selectedBarrios.size(); i++) {

                        List<String> restoIndex = new ArrayList<>();

                        restoIndex.add(selectedBarrios.get(i));

                        Iterable<Restaurant> restos = repository.findAllByBarrio(restoIndex);

                        restos.forEach(resto -> restaurantes.add(resto));

                    }
                }
                restaurantesFiltrados(restaurantes);

            } else {

                List<Restaurant> restaurantes = new ArrayList<>();

                List<Restaurant> byComida = new ArrayList<>();

                for (int i = 0; i < selectedItemsComidas.size(); i++) {

                    List<String> restoIndex = new ArrayList<>();

                    restoIndex.add(selectedItemsComidas.get(i));

                    Iterable<Restaurant> restos = repository.findAllByGrupoDeComidaList(grupoDeComidaRepository.getGrupoDeComidaByGrupo(restoIndex));

                    restos.forEach(resto -> byComida.add(resto));

                }

                for (int j = 0; j < byComida.size(); j++) {

                    for (int n = 0; n < selectedBarrios.size(); n++) {
                        if (byComida.get(j).getBarrio().equals(selectedBarrios.get(n))) {
                            restaurantes.add(byComida.get(j));
                        }
                    }
                }

                restaurantesFiltrados(restaurantes);

            }

        } else {
            Restaurant resto = repository.findByNombreRestaurant(restaurantToFind.getText());
            if (resto != null) {

                ArrayList<Restaurant> restoFound = new ArrayList();
                restoFound.add(resto);

                restaurantesFiltrados(restoFound);

            }
        }
    }

    @FXML
    void reservar(ActionEvent event) {

    }

    public void restaurantesFiltrados(List<Restaurant> restaurants) {

        ObservableList<Restaurant> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < restaurants.size(); i++) {
            observableList.add(restaurants.get(i));
        }

        nombreRestaurante.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("nombreRestaurant"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("descripcion"));
        barrio.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("barrio"));
        imagen.setCellValueFactory(new PropertyValueFactory<Restaurant, ImageView>("imageView"));
        cocinas.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("cocinasOfrecidasString"));

        tableViewRestaurantes.setItems(observableList);

        tableViewRestaurantes.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked((event1) -> {
                if (event1.getClickCount() == 2 && (!row.isEmpty())) {
                    restaurant = row.getItem();
                    try {
                        loadRestaurantEspecifico(event1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });


    }

    private void loadRestaurantEspecifico(MouseEvent event1) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(AppApplication.getContext()::getBean);
        Parent root = loader.load(Principal.class.getResourceAsStream("restaurantEspecificoP.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Restaurant específico");
        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        double w = ((Stage)((Node)event1.getSource()).getScene().getWindow()).getWidth();
        double h = ((Stage)((Node)event1.getSource()).getScene().getWindow()).getHeight();
        stage.setScene(new Scene(root));
        stage.setHeight(h);
        stage.setWidth(w);


        numeroMesa.setCellValueFactory((new PropertyValueFactory<Mesa,String>("nroReferencia")));
        sillas.setCellValueFactory(new PropertyValueFactory<Mesa,String>("cantLugares"));

        List<Mesa> mesasList = (List) restaurantService.getByEmail(restaurant.getEmail()).getMesas();
        mesas.setItems(FXCollections.observableList(mesasList));

        nombre.setText(restaurant.getNombreRestaurant());
        description.setText(restaurant.getDescripcion());
        barrioRE.setText(restaurant.getBarrio());
        precioMedio.setText(restaurant.getPrecioMedio());
        tel.setText(restaurant.getTelefono());
        direccion.setText(restaurant.getDireccion());
        horario.setText(restaurant.getHorarioApertura() + " - " + restaurant.getHorarioCierre());
        description.setText(restaurant.getDescripcion());
        imagenRE.setImage(restaurant.getImageView().getImage());
        comidas.setText(restaurant.getCocinasOfrecidasString());
        pagos.setText(restaurant.getTipoDePagoListString());
        stage.show();
    }

    @FXML
    void ajustes(ActionEvent event) {
        //Modificar datos
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(AppApplication.getContext()::getBean);
        Parent root = null;
        try {
            root = loader.load(LoginController.class.getResourceAsStream("login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("¡Bienvenido!");
        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
        double w = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getWidth();
        double h = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getHeight();
        stage.setScene(new Scene(root));
        stage.setHeight(h);
        stage.setWidth(w);
        stage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void atras(ActionEvent e){
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void reservas(ActionEvent event) {
        try {
            if(reservaService.getReservasByCliente(cliente).size() == 0){
                throw new ClienteSinReservas("Aun no tienes ninguna reserva!");
            };
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(AppApplication.getContext()::getBean);
            Parent root = null;
            try {
                root = loader.load(VerReservas.class.getResourceAsStream("verReservas.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Tus reservas");
            stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
            double w = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getWidth();
            double h = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getHeight();
            stage.setScene(new Scene(root));
            stage.setHeight(h);
            stage.setWidth(w);
            stage.show();

            VerReservas verReservas = loader.<VerReservas>getController();
            verReservas.setCliente(cliente);
        }catch(ClienteSinReservas e){
            showAlert("Reservas", e.getMessage());
        }
    }
    @FXML
    void solicitarMesa(ActionEvent event) {
        Mesa mesa = mesas.getSelectionModel().getSelectedItem();
        try {
            reservaService.solicitarReserva(cliente, restaurant, mesa.getNroReferencia());
            showAlert("Solicitado", "Se ha enviado una solicitud de reserva al restaurante.");
        }catch (ReservaYaSolicitada e){
            showAlert("Error", e.getMessage());
        }

    }
}