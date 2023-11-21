package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.SeleccionarElementoException;
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

import java.io.IOException;

public class VtnConsulDestinosController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    private AnchorPane panel;

    @FXML
    private TextField TxtDestinos;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<Destino, String> colCiudad;

    @FXML
    private TableColumn<Destino, String> colClima;

    @FXML
    private TableColumn<Destino, String> colDescripcion;

    @FXML
    private TableColumn<Destino, String> colNombre;

    @FXML
    private TableView<Destino> tablaDestinos;
    public void init(AnchorPane panel) {
        this.panel=panel;

        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        colDescripcion.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colClima.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClima().name()));

        ObservableList<Destino> listaDestinos = FXCollections.observableArrayList(agenciaCliente.getListaDestinos());
        tablaDestinos.setItems(listaDestinos);
    }

    public void eliminar() {
        Destino destino=tablaDestinos.getSelectionModel().getSelectedItem();
        try
        {
            if (agenciaCliente.verificarDestino(destino))
            {
                agenciaCliente.eliminarDestinoPaquetes(0,destino);
                agenciaCliente.eliminarDestino(destino);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("El destino se ha eliminado correctamente de nuestra base de datos, ademas, el destino ha sido retirado de todos los paquetes que lo incluian ");
                alert.setHeaderText(null);
                alert.show();

                ObservableList<Destino> listaDestinos = FXCollections.observableArrayList(agenciaCliente.getListaDestinos());
                tablaDestinos.setItems(listaDestinos);
            }
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }

    public void editelo() {
        Destino destino=tablaDestinos.getSelectionModel().getSelectedItem();

            try {
                if(agenciaCliente.verificarDestino(destino)){
                // Cargar el FXML de la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnEditarDestinos.fxml"));
                Pane nuevaVentana = loader.load();
                VtnEditarDestinosController editarDestinoController=loader.getController();
                editarDestinoController.init(panel);


                // Limpiar el contenido anterior y establecer el nuevo contenido
                panel.getChildren().clear();
                panel.getChildren().add(nuevaVentana);
            }
            } catch (SeleccionarElementoException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
        }
        VtnEditarDestinosController.destino=destino;
    }


    public void regresar(){
        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestDestino.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestDestinoController gestDestinoController=loader.getController();
            gestDestinoController.init(panel);


            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
