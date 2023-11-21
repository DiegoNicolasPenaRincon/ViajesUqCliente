package co.edu.uniquindio.agencia.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VtnAdminController {

    @FXML
    private AnchorPane PanelBotones;

    @FXML
    private Button btnDestinos;

    @FXML
    private Button btnEstadisticas;

    @FXML
    private Button btnGuias;

    @FXML
    private Button btnPaqTuristicos;

    @FXML
    private AnchorPane panelVentanas;
    private Stage stage;
    private VtnInicioSesionController vtnInicioSesionController;
    public void init(Stage stage, VtnInicioSesionController vtnInicioSesionController) {
        this.stage=stage;
        this.vtnInicioSesionController=vtnInicioSesionController;
    }

    public void mostrarVentanaDestinos(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestDestino.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestDestinoController gestDestinoController=loader.getController();
            gestDestinoController.init(panelVentanas);

            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentanas.getChildren().clear();
            panelVentanas.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaPaquetes(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestPaquetes.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestPaquetesController gestPaquetesController=loader.getController();
            gestPaquetesController.init(panelVentanas);

            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentanas.getChildren().clear();
            panelVentanas.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaGuias(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestiGuias.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestiGuiasController gestiGuiasController=loader.getController();
            gestiGuiasController.init(panelVentanas);

            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentanas.getChildren().clear();
            panelVentanas.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaEstadisticas(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnEstadisticas.fxml"));
            Pane nuevaVentana = loader.load();

            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentanas.getChildren().clear();
            panelVentanas.getChildren().add(nuevaVentana);
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
