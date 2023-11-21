package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Destino;
import co.edu.uniquindio.agencia.model.GuiasTuristicos;
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

public class VtnConsGuiasController {
    @FXML
    public TextField TxtBuscarGuias;
    @FXML
    public Button btnConsultar;
    @FXML
    public TableView<GuiasTuristicos> tableGuias;

    @FXML
    public Button btnRegresar;
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnEditar;
    @FXML
    public TableColumn<GuiasTuristicos,String> Idiomas;
    @FXML
    public TableColumn<GuiasTuristicos,String> Experiencia;
    @FXML
    public TableColumn<GuiasTuristicos,String> IDGuia;
    @FXML
    public TableColumn<GuiasTuristicos,String> Nombre;
    private AnchorPane panel;

    private final AgenciaCliente agenciaViajes=AgenciaCliente.getInstance();

    public void init(AnchorPane panel) {
        this.panel=panel;
        Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        IDGuia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        Experiencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExperiencia()));

        ObservableList<GuiasTuristicos> listaGuias = FXCollections.observableArrayList(agenciaViajes.getListaGuias());
        tableGuias.setItems(listaGuias);
    }

    public void eliminarGuia() {
        GuiasTuristicos guia=tableGuias.getSelectionModel().getSelectedItem();
        agenciaViajes.eliminarGuiaTuristico(String.valueOf(guia),0);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("El guia ha sido eliminado con exito");
        alert.setHeaderText(null);
        alert.show();

        ObservableList<GuiasTuristicos> listaGuias = FXCollections.observableArrayList(agenciaViajes.getListaGuias());
        tableGuias.setItems(listaGuias);
    }
    public void regresar(){
        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestiGuias.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestiGuiasController gestiGuiasController=loader.getController();
            gestiGuiasController.init(panel);


            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
}
}
}
