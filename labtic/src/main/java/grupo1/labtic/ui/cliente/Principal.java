package grupo1.labtic.ui.cliente;

import grupo1.labtic.AppApplication;
import grupo1.labtic.persistence.GrupoDeComidaRepository;
import grupo1.labtic.persistence.RestaurantRepository;
import grupo1.labtic.services.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Principal {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private GrupoDeComidaRepository grupoDeComidaRepository;

    @FXML
    private MenuButton zonasToFindTable;
    @FXML
    private MenuButton comidasToFindTable;

    @FXML
    private TextField restaurantToFind;

    @FXML
    private CheckMenuItem puntaCarretas;
    @FXML
    private CheckMenuItem palermo;
    @FXML
    private CheckMenuItem parqueRodo;
    @FXML
    private CheckMenuItem maronias;
    @FXML
    private CheckMenuItem cordon;
    @FXML
    private CheckMenuItem buceo;
    @FXML
    private CheckMenuItem malvin;
    @FXML
    private CheckMenuItem ciudadVieja;
    @FXML
    private CheckMenuItem centro;
    @FXML
    private CheckMenuItem pocitos;
    @FXML
    private CheckMenuItem barrioSur;
    @FXML
    private CheckMenuItem parqueBatlle;
    @FXML
    private CheckMenuItem puntaGorda;
    @FXML
    private CheckMenuItem carrasco;

    @FXML
    private CheckMenuItem comidaVegana;
    @FXML
    private CheckMenuItem parrilla;
    @FXML
    private CheckMenuItem sandwiches;
    @FXML
    private CheckMenuItem cafeteria;
    @FXML
    private CheckMenuItem comidaChina;
    @FXML
    private CheckMenuItem celiacos;
    @FXML
    private CheckMenuItem comidaMexicana;
    @FXML
    private CheckMenuItem wok;
    @FXML
    private CheckMenuItem hamburgesas;
    @FXML
    private CheckMenuItem pizza;
    @FXML
    private CheckMenuItem chivitos;
    @FXML
    private CheckMenuItem sushi;
    @FXML
    private CheckMenuItem tartas;
    @FXML
    private CheckMenuItem pescadoMariscos;
    @FXML
    private CheckMenuItem comidaVegetariana;
    @FXML
    private CheckMenuItem ensaladas;
    @FXML
    private CheckMenuItem wrap;
    @FXML
    private CheckMenuItem milanesas;


    @FXML
    private Text nombre;
    @FXML
    private Text description;
    @FXML
    private Text barrioPM;
    @FXML
    private Text direccion;
    @FXML
    private Text tel;
    @FXML
    private Text horario;
    @FXML
    private HBox hBox;
    @FXML
    private ImageView logo;
    @FXML
    private Text comidas;
    @FXML
    private Text pagos;

    @FXML
    private Button reservar;
    @FXML
    private TextField personas;
    @FXML
    private TextField hora;
    @FXML
    private TextField min;
    @FXML
    private DatePicker fechaReserva;

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

    private String mailResto;

    private Restaurant rowData;


    @FXML
    void initialize() {
        nombreRestaurante.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("nombreRestaurant"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("descripcion"));
        barrio.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("barrio"));
        imagen.setCellValueFactory(new PropertyValueFactory<Restaurant, ImageView>("imageView"));
        cocinas.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("cocinasOfrecidas"));

        List<Restaurant> restaurantes = (List) repository.findAll();
        tableViewRestaurantes.setItems(FXCollections.observableList(restaurantes));


        tableViewRestaurantes.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked((event1) -> {
                if (event1.getClickCount() == 2 && (!row.isEmpty())) {
                    rowData = row.getItem();
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(AppApplication.getContext()::getBean);
                        Parent root = loader.load(Principal.class.getResourceAsStream("restaurantEspecifico.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Restaurant específico");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        stage.setScene(new Scene(root));
                        nombre.setText(rowData.getNombreRestaurant());
                        description.setText(rowData.getDescripcion());
                        barrioPM.setText(rowData.getBarrio() + " - " + rowData.getPrecioMedio());
                        tel.setText(rowData.getTelefono());
                        direccion.setText(rowData.getDireccion());
                        horario.setText(rowData.getHorarioApertura() + " - " + rowData.getHorarioCierre());
                        description.setText(rowData.getDescripcion());
                        if (rowData.getImagen() != null) {
                            logo.setImage(rowData.getImageView().getImage());
                        }
                        comidas.setText(rowData.getCocinasOfrecidasString());
                        pagos.setText(rowData.getTipoDePagoListString());
                        mailResto = rowData.getEmail();
                        stage.show();
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
        cocinas.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("cocinasOfrecidas"));

        tableViewRestaurantes.setItems(observableList);

        tableViewRestaurantes.setRowFactory(tv -> {
            TableRow<Restaurant> row = new TableRow<>();
            row.setOnMouseClicked((event1) -> {
                if (event1.getClickCount() == 2 && (!row.isEmpty())) {
                    rowData = row.getItem();
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setControllerFactory(AppApplication.getContext()::getBean);
                        Parent root = loader.load(Principal.class.getResourceAsStream("restaurantEspecifico.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Restaurant específico");
                        stage.getIcons().add(new Image("grupo1/labtic/ui/Imagenes/yendoIcono.png"));
                        stage.setScene(new Scene(root));
                        nombre.setText(rowData.getNombreRestaurant());
                        description.setText(rowData.getDescripcion());
                        barrioPM.setText(rowData.getBarrio() + " - " + rowData.getPrecioMedio());
                        tel.setText(rowData.getTelefono());
                        direccion.setText(rowData.getDireccion());
                        horario.setText(rowData.getHorarioApertura() + " - " + rowData.getHorarioCierre());
                        description.setText(rowData.getDescripcion());
                        logo.setImage(rowData.getImageView().getImage());
                        comidas.setText(rowData.getCocinasOfrecidasString());
                        pagos.setText(rowData.getTipoDePagoListString());
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });


    }


}

