package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.MalaFechaException;
import co.edu.uniquindio.agencia.exceptions.NegativosException;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Destino;
import co.edu.uniquindio.agencia.model.PaqueteTuristico;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Persistencia_Serializacion;

import java.io.FileNotFoundException;
import java.io.IOException;

public class VtnEditarPaquetesController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();

    @FXML
    private TableColumn<Destino, String> colNombre1;

    @FXML
    private TableColumn<Destino, String> colCiudad1;

    @FXML
    private TableColumn<Destino, String> colClima1;

    @FXML
    private TableView<Destino> TableDestinosPaquete;

    @FXML
    private DatePicker DateFinal;

    @FXML
    private DatePicker DateInicio;

    @FXML
    private TableView<Destino> TableDestinos;

    @FXML
    private TextField TxtPrecio;

    @FXML
    private Button btnAgDestinos;

    @FXML
    private Button btnElimianrDestino;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<Destino, String> colCiudad;

    @FXML
    private TableColumn<Destino, String> colClima;

    @FXML
    private TableColumn<Destino, String> colNombre;

    @FXML
    private TextField txtCaracteristicas;

    @FXML
    private TextField txtCupo;

    @FXML
    private Spinner<Integer> txtDuracion;

    @FXML
    private TextField txtNombre;

    private AnchorPane panel;
    private PaqueteTuristico paquete;

    private String nombreAntiguo;

    public void init(PaqueteTuristico paquete, AnchorPane panel) {
        this.paquete=paquete;
        this.panel=panel;
        //this.nombreAntiguo=paquete.getNombre();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, Integer.MAX_VALUE, paquete.getDuracion()); // Rango y valor inicial
        txtDuracion.setValueFactory(valueFactory);

        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        colClima.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClima().name()));

        colNombre1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        colClima1.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClima().name()));

        ObservableList<Destino> listaDestinos = FXCollections.observableArrayList(agenciaCliente.getListaDestinos());
        int indicePaquete= agenciaCliente.getListaPaquetes().indexOf(paquete);
        ObservableList<Destino> listaDestinosPaquetes=FXCollections.observableArrayList(agenciaCliente.getListaPaquetes().get(indicePaquete).getDestinos());
        TableDestinos.setItems(listaDestinos);
        TableDestinosPaquete.setItems(listaDestinosPaquetes);

       /* txtNombre.setText(paquete.getNombre());
        txtCaracteristicas.setText(paquete.getServicios());
        txtCupo.setText(String.valueOf(paquete.getCupoMaximo()));
        TxtPrecio.setText(String.valueOf(paquete.getPrecio()));

        */
    }

    public void editarPaquete(){
        try
        {
            String apoyoDuracion=""+txtDuracion.getValue();
            agenciaCliente.modificarPaquete2(0,paquete,txtNombre.getText(),"pruebelos",apoyoDuracion,TxtPrecio.getText(),txtCupo.getText(),DateInicio.getValue(),DateFinal.getValue());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Se han cambiado o conservado los atributos del paquete.");
            alert.setHeaderText(null);
            alert.show();

            Persistencia_Serializacion.serializarObjetoXMLConLocalDate(agenciaCliente.rutaPaquetes, agenciaCliente.getListaPaquetes());
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (MalaFechaException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
        catch (NegativosException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ni el precio, ni la duracion del paquete, ni el cupo maximo del paquete, pueden ser valores negativos");
            alert.setHeaderText(null);
            alert.show();
        }

    }

    public void regresar(){
        try {

            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnConsPaquetes.fxml"));
            Pane nuevaVentana = loader.load();
            VtnConsPaquetesController consPaquetesController=loader.getController();
            consPaquetesController.init(panel);


            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public void eliminarDestino() {
        Destino destino=TableDestinosPaquete.getSelectionModel().getSelectedItem();
        if(destino!=null)
        {
            try
            {
                agenciaCliente.eliminarDestinoPaquetes(0,destino);

                ObservableList<Destino> listaDestinos = FXCollections.observableArrayList(agenciaCliente.getListaDestinos());
                int indicePaquete= agenciaCliente.getListaPaquetes().indexOf(paquete);
                ObservableList<Destino> listaDestinosPaquetes=FXCollections.observableArrayList(agenciaCliente.getListaPaquetes().get(indicePaquete).getDestinos());
                TableDestinos.setItems(listaDestinos);
                TableDestinosPaquete.setItems(listaDestinosPaquetes);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El destino se ha eliminado con exitos");
                alert.setHeaderText(null);
                alert.show();

            }
            catch (FileNotFoundException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Estamos teniendo problemas con nuestra base de datos");
                alert.setHeaderText(null);
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Seleccione el destino que desea eliminar");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    public void agregarDestino() {
        Destino destino=TableDestinos.getSelectionModel().getSelectedItem();
        if(destino!=null)
        {
            try
            {
                agenciaCliente.agregarDestinoPaquete(0,destino,paquete);
                ObservableList<Destino> listaDestinos = FXCollections.observableArrayList(agenciaCliente.getListaDestinos());
                int indicePaquete= agenciaCliente.getListaPaquetes().indexOf(paquete);
                ObservableList<Destino> listaDestinosPaquetes=FXCollections.observableArrayList(agenciaCliente.getListaPaquetes().get(indicePaquete).getDestinos());
                TableDestinos.setItems(listaDestinos);
                TableDestinosPaquete.setItems(listaDestinosPaquetes);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El destino se ha agregado con exito");
                alert.setHeaderText(null);
                alert.show();
            }
            catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Seleccione el destino que desea agregar");
            alert.setHeaderText(null);
            alert.show();
        }
    }
}

