package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clientes;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VtnInicioSesionController {

    private Clientes cliente;
    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    @FXML
    private PasswordField TxtContraseña;

    @FXML
    private TextField TxtUsuario;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    private Stage stage;

    public void setStage(Stage PrimaryStage) {
        stage=PrimaryStage;
    }

    public void mostrarVentanaRegistro() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Ventanas/VtnRegistro.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        VtnRegistroController controller =loader.getController();
        controller.init(stage, this);
        stage.show();
    }

    public void mostrarVentanaMenu() throws IOException {
        String contrasena=TxtContraseña.getText();
        String nombre=TxtUsuario.getText();
        String tipo= agenciaCliente.verificarInicio(nombre, contrasena, 0, 0);

        if(tipo.equals("cliente")){
            cliente= agenciaCliente.buscarCliente(nombre, contrasena, 0);
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Ventanas/VtnClientes.fxml"));
            Parent root=loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            VtnClientesController controller =loader.getController();
            controller.init(stage, this, cliente);
            stage.show();
            this.stage.close();

        }
        else if(tipo.equals("admin")){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Ventanas/VtnAdmin.fxml"));
            Parent root=loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            VtnAdminController controller =loader.getController();
            controller.init(stage, this);
            stage.show();
            this.stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Usuario no encontrado, verifique las credenciales");
            alert.show();
        }

    }
}