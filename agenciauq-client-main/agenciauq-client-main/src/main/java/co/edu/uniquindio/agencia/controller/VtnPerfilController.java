package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.model.Clientes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VtnPerfilController implements Initializable {

    @FXML
    private Button btnEditar;

    @FXML
    private Label lblCorreo;

    @FXML
    private Label lblDireccion;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblTelefono;


    private Clientes cliente;
    private AnchorPane panelVentana;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void init(Clientes cliente, AnchorPane panelVentana) {
        this.cliente=cliente;
        this.panelVentana=panelVentana;
        lblNombre.setText(cliente.getNombre());
        lblCedula.setText(cliente.getCedula());
        lblCorreo.setText(cliente.getCorreo());
        lblTelefono.setText(cliente.getTelefono());
        lblDireccion.setText(cliente.getDireccion());
    }

    public void mostrarEditarPerfil(){
        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnEditarPerfil.fxml"));
            Pane nuevaVentana = loader.load();
            VtnEditarPerfilController perfilController = loader.getController();
            perfilController.init(cliente, panelVentana);

            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentana.getChildren().clear();
            panelVentana.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
