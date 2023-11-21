package co.edu.uniquindio.agencia.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VtnGestPaquetesController {

    private AnchorPane panel;

    public void mostrarAgregarPaquetes(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnAgPaquetes.fxml"));
            Pane nuevaVentana = loader.load();
            VtnAgPaquetesController AgPaquetesController=loader.getController();
            AgPaquetesController.init(panel);
            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(AnchorPane panelVentanas) {
        this.panel=panelVentanas;
    }

    public void mostrarConsultarPaquetes(){
        try {
            // Cargar el FXML en el AnchorPane derecho
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
}
