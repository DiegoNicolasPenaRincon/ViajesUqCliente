package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clima;
import co.edu.uniquindio.agencia.model.Destino;
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

import java.io.IOException;
import java.util.ArrayList;

public class VtnEditarDestinosController {

    @FXML
    private ComboBox<Clima> boxClima;

    @FXML
    private ComboBox<String> boxEleccion;

    @FXML
    private Button btnCargarImagenes;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnRegresar;

    @FXML
    private ImageView imagen1;

    @FXML
    private ImageView imagen2;

    @FXML
    private ImageView imagen3;

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

    public static Destino destino;

    private final String [] eliminarOagregar={"Agregar","Eliminar"};

    private AnchorPane panel;
    public void init(AnchorPane panel) {
        this.panel=panel;
        boxClima.setItems(FXCollections.observableArrayList(Clima.values()));
        boxEleccion.setItems(FXCollections.observableArrayList(eliminarOagregar));

    }

    public void regresar(){
        try {
            // Cargar el FXML de la nueva ventana
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

    public void guardarCambios() {
        ArrayList<String> imagenesNuevas=new ArrayList<String>();
        try
        {
            String nombreActual=destino.getNombre();
            imagenesNuevas.add(txtImagen1.getText());
            imagenesNuevas.add(txtImagen2.getText());
            imagenesNuevas.add(txtImagen3.getText());
            if(destino!=null)
            {
                System.out.println("necesito saber si es nulo");
            }
            agenciaCliente.modificarDestinos(destino,0,txtDescripcion.getText(),imagenesNuevas,boxClima.getValue(),txtNombre.getText(),txtCiudad.getText(),boxEleccion.getValue());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El destino ha sido modificado con exito");
            alert.setHeaderText(null);
            alert.show();


        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }
}
