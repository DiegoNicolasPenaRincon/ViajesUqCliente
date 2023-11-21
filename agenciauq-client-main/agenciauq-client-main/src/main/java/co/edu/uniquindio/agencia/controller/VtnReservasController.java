package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.NoHayObjetoException;
import co.edu.uniquindio.agencia.exceptions.SeleccionarElementoException;
import co.edu.uniquindio.agencia.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class VtnReservasController {
    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    @FXML
    private TextField TxtBuscar;

    @FXML
    private Button btnCalificar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnbuscar;

    @FXML
    private TableColumn<Reservas, String> colEstado;

    @FXML
    private TableColumn<Reservas, String> colFecha;

    @FXML
    private TableColumn<Reservas, String> colGuia;

    @FXML
    private TableColumn<Reservas, String> colPaquete;

    @FXML
    private TableColumn<Reservas, String> colPersonas;

    @FXML
    private TableColumn<Reservas, String> colPrecio;

    @FXML
    private TableView<Reservas> tablaReservas;

    private AnchorPane panelVentana;
    private Clientes cliente;

    public void init(AnchorPane panelVentana, Clientes cliente) {
        this.panelVentana=panelVentana;
        this.cliente=cliente;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().name()));
        colPaquete.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaquete().getNombre()));
        colPersonas.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getNumPersonas())));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaViaje().format(formatter)));
        colGuia.setCellValueFactory(cellData -> {
            GuiasTuristicos guia = cellData.getValue().getGuiaTuristico();
            String guiaNombre = (guia != null) ? guia.getNombre() : "No";
            return new SimpleStringProperty(guiaNombre);
        });
        colPrecio.setCellValueFactory((cellData->new SimpleStringProperty(Double.toString(cellData.getValue().getPrecioTotal()))));

        ObservableList<Reservas> reservasCliente = FXCollections.observableArrayList(
                agenciaCliente.getListaReservas());
        tablaReservas.setItems(reservasCliente);

    }

    public void cancelarReserva(){
        Reservas reserva=tablaReservas.getSelectionModel().getSelectedItem();
        try {
            if(agenciaCliente.verificarReserva(reserva)){
                if(agenciaCliente.verificarEstado(reserva)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Esta seguro de cancelar su reserva?");
                    alert.setHeaderText(null);
                    // Agregar botones de confirmación y cancelación
                    ButtonType buttonTypeSi = new ButtonType("Sí");
                    ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

                    // Mostrar la alerta y esperar la respuesta del usuario
                    Optional<ButtonType> result = alert.showAndWait();

                    // Verificar la respuesta del usuario
                    if (result.isPresent() && result.get() == buttonTypeSi) {

                        agenciaCliente.cancelarReserva(reserva);

                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setContentText("Reserva cancelada");
                        alert2.setHeaderText(null);
                        alert2.show();

                        init(panelVentana, cliente);
                    }
                }
            }
        } catch (SeleccionarElementoException | NoHayObjetoException | FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }
}
