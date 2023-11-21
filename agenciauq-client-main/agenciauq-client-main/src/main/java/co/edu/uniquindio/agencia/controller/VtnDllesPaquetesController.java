package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clientes;
import co.edu.uniquindio.agencia.model.Destino;
import co.edu.uniquindio.agencia.model.PaqueteTuristico;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class VtnDllesPaquetesController {

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;


    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();

    @FXML
    private TableView<Destino> Destino;

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
    private Label lblCaracteristicas;

    @FXML
    private Label lblFinal;

    @FXML
    private Label lblInicio;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPrecio;

    private AnchorPane panelVentana;
    private PaqueteTuristico paqueteSeleccionado;

    private Clientes cliente;

    public void init(AnchorPane panelVentana, PaqueteTuristico paqueteSeleccionado, Clientes cliente) {
        this.panelVentana = panelVentana;
        this.paqueteSeleccionado = paqueteSeleccionado;
        this.cliente=cliente;


        lblNombre.setText(paqueteSeleccionado.getNombre());
        lblCaracteristicas.setText(paqueteSeleccionado.getServicios());
        lblInicio.setText(paqueteSeleccionado.getFechaInicio().toString());
        lblFinal.setText(paqueteSeleccionado.getFechaFin().toString());
        lblPrecio.setText(String.valueOf(paqueteSeleccionado.getPrecio()));

        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        colClima.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClima().name()));

        ObservableList<Destino> listaDestinos = FXCollections.observableArrayList(paqueteSeleccionado.getDestinos());
        Destino.setItems(listaDestinos);

        Destino.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Cambiar las imágenes en los ImageView con las fotos del destino seleccionado
                new Thread(() -> {
                    actualizarImagenesDestino(newValue);
                }).start();
            }
        });
    }

    private void actualizarImagenesDestino(Destino destinoSeleccionado) {
       try{
        // Obtener el ArrayList de fotos del destino
        List<String> fotosDestino = destinoSeleccionado.getRutasImagenes();

        // Actualizar las imágenes en los ImageView con las nuevas fotos
        for (int i = 0; i < fotosDestino.size(); i++) {
            String urlFoto = fotosDestino.get(i);
            // Obtener el ImageView correspondiente al índice
            ImageView imageView = obtenerImageViewPorIndice(i + 1);
            if (imageView != null) {
                // Configurar la nueva imagen en el ImageView
                Image nuevaImagen = new Image(urlFoto);
                Platform.runLater(() -> {
                    imageView.setImage(nuevaImagen);
                });
            }
        }
    }catch(IllegalArgumentException e){
           Platform.runLater(() -> {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("Imagenes no disponibles en este momento.");
               alert.setHeaderText(null);
               alert.show();
           });
        }
    }
    private ImageView obtenerImageViewPorIndice(int indice) {
        switch (indice) {
            case 1:
                return imageView1;
            case 2:
                return imageView2;
            case 3:
                return imageView3;
            default:
                return null;
        }
    }

    public void regresar(){
            try {
                // Cargar el FXML de la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnPtesCliente.fxml"));
                Pane nuevaVentana = loader.load();
                VtnPtesClienteController ptesClienteController=loader.getController();
                ptesClienteController.init(panelVentana, cliente);


                // Limpiar el contenido anterior y establecer el nuevo contenido
                panelVentana.getChildren().clear();
                panelVentana.getChildren().add(nuevaVentana);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

