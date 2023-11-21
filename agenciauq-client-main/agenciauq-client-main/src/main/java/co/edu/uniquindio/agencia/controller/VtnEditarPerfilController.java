package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.CampoObligatorioException;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clientes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VtnEditarPerfilController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    @FXML
    private PasswordField TxtNuevaContra;

    @FXML
    private TextField TxtNuevaResidencia;

    @FXML
    private TextField TxtNuevoCorreo;

    @FXML
    private TextField TxtNuevoNombre;

    @FXML
    private TextField TxtNuevoNumero;

    @FXML
    private Button btnAgNuevaFoto;

    @FXML
    private Button btnCambiar;

    @FXML
    private Button btnRegresar;
    private AnchorPane panelVentana;
    private Clientes cliente;
    private Stage stage;


    public void editarPerfil(){
        try {
            this.cliente= agenciaCliente.modificarCliente(
                    TxtNuevoNombre.getText(),
                    cliente.getCedula(),
                    TxtNuevaContra.getText(),
                    TxtNuevoNumero.getText(),
                    TxtNuevoCorreo.getText(),
                    TxtNuevaResidencia.getText(),
                    0);
            agenciaCliente.actualizarArchivoClientes();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha registrado correctamente el cliente ");
            alert.show();
        } catch (CampoObligatorioException e) {
            agenciaCliente.actualizarArchivoClientes();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    public void regresarPerfil(){
        try {
            // Cargar el FXML de la nueva ventana
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

    public void init(Clientes cliente, AnchorPane panelVentana) {
        this.cliente=cliente;
        this.panelVentana=panelVentana;
        TxtNuevoNombre.setText(cliente.getNombre());
        TxtNuevaContra.setText(cliente.getContrasena());
        TxtNuevoNumero.setText(cliente.getTelefono());
        TxtNuevoCorreo.setText(cliente.getCorreo());
        TxtNuevaResidencia.setText(cliente.getDireccion());
    }


}
