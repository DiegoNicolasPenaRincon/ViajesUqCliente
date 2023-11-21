package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.*;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Destino;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class VtnAgPaquetesController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();

    @FXML
    private Button btnQuitar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<Destino, String> colCiudad;

    @FXML
    private TableColumn<Destino, String> colClima;

    @FXML
    private TableColumn<Destino, String> colNombre;


    @FXML
    private DatePicker DateFiinal;

    @FXML
    private DatePicker DateInicio;

    @FXML
    private TableView<Destino> TableDestinos;

    @FXML
    private TextField TxtCteristicas;

    @FXML
    private TextField TxtPrecio;

    @FXML
    private Button btnAgDestinos;

    @FXML
    private Button btnAgregar;

    @FXML
    private TextField txtCupo;

    @FXML
    private Spinner<Integer> txtDuracion;

    @FXML
    private TextField txtNombre;
    private AnchorPane panel;

    private ArrayList<Destino> destinos;
    public void init(AnchorPane panel) {
        this.panel=panel;
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        colClima.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClima().name()));

        ObservableList<Destino> listaDestinos = FXCollections.observableArrayList(agenciaCliente.getListaDestinos());
        TableDestinos.setItems(listaDestinos);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, Integer.MAX_VALUE, 1); // Rango y valor inicial
        txtDuracion.setValueFactory(valueFactory);
    }

    public void regresar(){
        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestPaquetes.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestPaquetesController gestPaquetesController=loader.getController();
            gestPaquetesController.init(panel);


            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarPaquete(){
        try {
            agenciaCliente.crearPaquete(
                    txtNombre.getText(),
                    String.valueOf(txtDuracion.getValue()),
                    TxtPrecio.getText(),
                    txtCupo.getText(),
                    TxtCteristicas.getText(),
                    DateInicio.getValue(),
                    DateFiinal.getValue(),
                    destinos);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Nuevo paquete creado.");
            alert.setHeaderText(null);
            alert.show();
        } catch (CampoObligatorioException | IgualesException | MalaFechaException |
                 ValorInvalidoException | SeleccionarElementoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        public void quitarDestino(){
            Destino destino=TableDestinos.getSelectionModel().getSelectedItem();
            if(destinos==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No se ha agregado este elemento al paquete anteriormente.");
                alert.setHeaderText(null);
                alert.show();
            }
            try {
                if(agenciaCliente.verificarDestino(destino)){
                    if(!agenciaCliente.verificarDestinoEnLista(destino, destinos)){
                        destinos.remove(destino);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Se ha eliminado el destino del paquete");
                        alert.setHeaderText(null);
                        alert.show();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Este destino no se ha agregado.");
                        alert.setHeaderText(null);
                        alert.show();
                    }
                }
            } catch (SeleccionarElementoException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            }
        }



    public void agregarDestinoAlPaquete(){
        Destino destino=TableDestinos.getSelectionModel().getSelectedItem();
        if(destinos==null){
            this.destinos=new ArrayList<>();
        }

        try {
            if(agenciaCliente.verificarDestino(destino)){
                if(agenciaCliente.verificarDestinoEnLista(destino, destinos)){
                    destinos.add(destino);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Se ha agregado el destino");
                    alert.setHeaderText(null);
                    alert.show();

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Este destino ya fue agregado.");
                    alert.setHeaderText(null);
                    alert.show();
                }
            }
        } catch (SeleccionarElementoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }


}