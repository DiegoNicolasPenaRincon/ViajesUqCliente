package co.edu.uniquindio.agencia.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VtnGestDestinoController {
    private AnchorPane panel;

    public void abrirAgregarDestino(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnAgregarDestinos.fxml"));
            Pane nuevaVentana = loader.load();
            VtnAgregarDestinosController vtnAgregarDestinosController=loader.getController();
            vtnAgregarDestinosController.init(panel);
            // Limpiar el contenido anterior y respectable el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirConsultarDestino(){
        try {
            // Cargar el FXML en el AnchorPane derecho
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnConsulDestinos.fxml"));
            Pane nuevaVentana = loader.load();
            VtnConsulDestinosController consulDestinosController=loader.getController();
            consulDestinosController.init(panel);
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
}
