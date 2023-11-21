package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.SeleccionarElementoException;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clientes;
import co.edu.uniquindio.agencia.model.PaqueteTuristico;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class VtnPtesClienteController implements Initializable {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    @FXML
    private TextField TxtBuscar;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private Button btnDetalles;

    @FXML
    private TableColumn<PaqueteTuristico, String> colCupos;

    @FXML
    private TableColumn<PaqueteTuristico, String> colFechaFinal;

    @FXML
    private TableColumn<PaqueteTuristico, String> colFechaInicio;

    @FXML
    private TableColumn<PaqueteTuristico, String> colNombre;

    @FXML
    private TableColumn<PaqueteTuristico, String> colPrecio;

    @FXML
    private TableView<PaqueteTuristico> tablaDestinos;

    private AnchorPane panelVentana;
    private Clientes cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCupos.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCupoMaximo())));
        colFechaInicio.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaInicio().format(formatter)));
        colFechaFinal.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaFin().format(formatter)));
        colPrecio.setCellValueFactory((cellData->new SimpleStringProperty(Double.toString(cellData.getValue().getPrecio()))));

        ObservableList<PaqueteTuristico> listaPaquetes = FXCollections.observableArrayList(agenciaCliente.getListaPaquetes());
        tablaDestinos.setItems(listaPaquetes);

        ScrollPane scrollPane = new ScrollPane(panelVentana);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

    public void verDetalles()  {
        PaqueteTuristico paqueteSeleccionado=tablaDestinos.getSelectionModel().getSelectedItem();
        try {
            if(agenciaCliente.verificarPaquete(paqueteSeleccionado)){
                try {
                    // Cargar el FXML en el AnchorPane derecho
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnDllesPaquetes.fxml"));
                    Pane nuevaVentana = loader.load();

                    VtnDllesPaquetesController dllesPaquetesController=loader.getController();
                    dllesPaquetesController.init(panelVentana, paqueteSeleccionado, cliente);

                    // Limpiar el contenido anterior y establecer el nuevo contenido
                    panelVentana.getChildren().clear();
                    panelVentana.getChildren().add(nuevaVentana);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SeleccionarElementoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }

    public void init(AnchorPane panelVentana, Clientes cliente) {
        this.panelVentana=panelVentana;
        this.cliente=cliente;
    }

    public void abrirCrearReserva(){
        PaqueteTuristico paqueteSeleccionado=tablaDestinos.getSelectionModel().getSelectedItem();
        try {
            if(agenciaCliente.verificarPaquete(paqueteSeleccionado)){
                try {
                    // Cargar el FXML en el AnchorPane derecho
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnReservar.fxml"));
                    Pane nuevaVentana = loader.load();

                    VtnReservarController crearReserva=loader.getController();
                    crearReserva.init(panelVentana, paqueteSeleccionado, cliente);


                    // Limpiar el contenido anterior y establecer el nuevo contenido
                    panelVentana.getChildren().clear();
                    panelVentana.getChildren().add(nuevaVentana);
                } catch (IOException e) {
                    e.printStackTrace();
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
