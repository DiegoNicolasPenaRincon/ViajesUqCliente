package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.CampoObligatorioException;
import co.edu.uniquindio.agencia.exceptions.IgualesException;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clientes;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VtnRegistroController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    private VtnInicioSesionController VtnInicioSesionController;
    private Stage stage;

    @FXML
    private TextField TxtCedula;

    @FXML
    private TextField TxtContrasena;

    @FXML
    private TextField TxtCorreo;

    @FXML
    private TextField TxtDireccion;

    @FXML
    private TextField TxtNombre;

    @FXML
    private TextField TxtNumero;

    @FXML
    private Button btnRegistrarse;

    public void init(Stage stage, VtnInicioSesionController vtnInicioSesionController) {
        this.VtnInicioSesionController=vtnInicioSesionController;
        this.stage=stage;
    }

    public void registrarCliente(){
        try{
            Clientes cliente= agenciaCliente.registrarCliente(
                    TxtNombre.getText(),
                    TxtCedula.getText(),
                    TxtContrasena.getText(),
                    TxtNumero.getText(),
                    TxtCorreo.getText(),
                    TxtDireccion.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha registrado correctamente el cliente "+cliente.getNombre());
            alert.show();
            stage.close();
        } catch (CampoObligatorioException | IgualesException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();

        } catch (
        IOException e) {
            throw new RuntimeException(e);
        }
    }
}
