package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.model.Clientes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VtnClientesController {

    private Stage stage;
    private VtnInicioSesionController vtnInicioSesionController;

    private Clientes cliente;
    @FXML
    private Button btnPaqTristicos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnRecomendaciones;

    @FXML
    private Button btnReservas;

    @FXML
    private AnchorPane panelBotones;

    @FXML
    private AnchorPane panelVentana;

    public void init(Stage stage, VtnInicioSesionController vtnInicioSesionController, Clientes cliente) {
        this.stage=stage;
        this.vtnInicioSesionController=vtnInicioSesionController;
        this.cliente=cliente;
    }



    public void mostrarVentanaPerfil(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnPerfil.fxml"));
            Pane nuevaVentana = loader.load();

            VtnPerfilController perfilController = loader.getController();
            perfilController.init(cliente, panelVentana);
            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentana.getChildren().clear();
            panelVentana.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPaquetes(){
        try {
            // Cargar el FXML en el AnchorPane derecho
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

    public void mostrarRecomendaciones(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnRecomendaciones.fxml"));
            Pane nuevaVentana = loader.load();

            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentana.getChildren().clear();
            panelVentana.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarReservas(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnReservas.fxml"));
            Pane nuevaVentana = loader.load();
            VtnReservasController reservasController=loader.getController();
            reservasController.init(panelVentana, cliente);
            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentana.getChildren().clear();
            panelVentana.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarSesion() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Ventanas/VtnInicioSesion.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        VtnInicioSesionController inicioSesionController=loader.getController();
        inicioSesionController.setStage(this.stage);
        stage.show();
        this.stage.close();

    }
}
