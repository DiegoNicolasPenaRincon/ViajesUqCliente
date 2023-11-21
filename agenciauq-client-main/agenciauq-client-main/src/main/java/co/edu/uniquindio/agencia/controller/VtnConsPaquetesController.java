package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.SeleccionarElementoException;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.PaqueteTuristico;
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
import java.time.format.DateTimeFormatter;

public class VtnConsPaquetesController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    @FXML
    private TextField TxtBuscarPtes;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnDetalles;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<PaqueteTuristico, String> colCupo;

    @FXML
    private TableColumn<PaqueteTuristico, String> colFinal;

    @FXML
    private TableColumn<PaqueteTuristico, String> colInicio;

    @FXML
    private TableColumn<PaqueteTuristico, String> colNombre;

    @FXML
    private TableColumn<PaqueteTuristico, String> colPrecio;

    @FXML
    private TableView<PaqueteTuristico> tablaPtes;

    private AnchorPane panel;

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

    public void eliminar() {
        PaqueteTuristico paquete=tablaPtes.getSelectionModel().getSelectedItem();
        try
        {
            agenciaCliente.eliminarPaqueteTuristico(paquete);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El destino ha sido eliminado con exito");
            alert.setHeaderText(null);
            alert.show();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void init(AnchorPane panel) {
        this.panel=panel;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCupo.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCupoMaximo())));
        colInicio.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaInicio().format(formatter)));
        colFinal.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaFin().format(formatter)));
        colPrecio.setCellValueFactory((cellData->new SimpleStringProperty(Double.toString(cellData.getValue().getPrecio()))));

        ObservableList<PaqueteTuristico> listaPaquetes= FXCollections.observableArrayList(agenciaCliente.getListaPaquetes());
        tablaPtes.setItems(listaPaquetes);

    }

    public void abrirEditar(){
        PaqueteTuristico paquete=tablaPtes.getSelectionModel().getSelectedItem();
        try {
            if (agenciaCliente.verificarPaquete(paquete)){
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnEditarPaquetes.fxml"));
            Pane nuevaVentana = loader.load();
            VtnEditarPaquetesController paquetesController=loader.getController();

            paquetesController.init(paquete, panel);

            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
            }
        } catch (IOException |SeleccionarElementoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }
}

