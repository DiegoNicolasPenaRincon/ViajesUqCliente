package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.CampoObligatorioException;
import co.edu.uniquindio.agencia.exceptions.IgualesException;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clima;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class VtnAgregarDestinosController {

    @FXML
    private ComboBox<Clima> boxClima;

    @FXML
    private Button btnRegresar;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtImagen1;

    @FXML
    private TextField txtImagen2;

    @FXML
    private TextField txtImagen3;

    @FXML
    private TextField txtNombre;

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    private AnchorPane panel;
    public void init(AnchorPane panel) {
        this.panel=panel;
        boxClima.setItems(FXCollections.observableArrayList(Clima.values()));
    }

    public void regresar(){
        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestDestino.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestDestinoController gestDestinoController=loader.getController();
            gestDestinoController.init(panel);


            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarDestino(){
        try{
        agenciaCliente.verificarURLS(
                txtImagen1.getText(),
                txtImagen2.getText(),
                txtImagen3.getText());

        ArrayList<String> rutaImagenes=new ArrayList<>();
        rutaImagenes.add(txtImagen1.getText());
        rutaImagenes.add(txtImagen2.getText());
        rutaImagenes.add(txtImagen3.getText());

            agenciaCliente.agregarDestino(
                    txtNombre.getText(),
                    txtCiudad.getText(),
                    txtDescripcion.getText(),
                    rutaImagenes,
                    boxClima.getValue());

            agenciaCliente.mensajeInformativo("El destino se ha creado exitosamente");

            txtImagen1.clear();
            txtImagen2.clear();
            txtImagen3.clear();
            txtCiudad.clear();
            txtNombre.clear();
            txtDescripcion.clear();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("El destino se ha creado exitosamente ");
            alert.setHeaderText(null);
            alert.show();

        }
        catch (IgualesException | CampoObligatorioException  e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}
