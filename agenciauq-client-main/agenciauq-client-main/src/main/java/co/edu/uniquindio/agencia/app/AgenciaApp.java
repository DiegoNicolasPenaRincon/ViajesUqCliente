package co.edu.uniquindio.agencia.app;

import co.edu.uniquindio.agencia.controller.VtnInicioSesionController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AgenciaApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        String serverAddress = "localhost"; // Dirección IP del servidor
        int serverPort = 1234; // Puerto del servidor
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            socket.close(); // Se conectó exitosamente, cerramos el socket

            FXMLLoader loader = new FXMLLoader(AgenciaApp.class.getResource("/Ventanas/VtnInicioSesion.fxml"));
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Agencia de Viajes UQ");
            VtnInicioSesionController controller = loader.getController();
            controller.setStage(stage);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Si hay una excepción (por ejemplo, no se pudo conectar), cerramos la aplicación
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No se pudo conectar al servidor. La aplicación se cerrará.");
                alert.setHeaderText(null);
                alert.showAndWait();
                Platform.exit();
            });
        }
    }

    public static void main(String[] args) {
        launch( AgenciaApp.class, args );

    }

}
