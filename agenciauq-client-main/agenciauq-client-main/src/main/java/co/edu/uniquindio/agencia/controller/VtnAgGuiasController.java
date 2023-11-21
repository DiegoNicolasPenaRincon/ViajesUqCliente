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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VtnAgGuiasController {
    @FXML
    public ImageView JboxIdiomas;
    @FXML
    public TextField TxtId;
    @FXML
    public TextField TxtNombre;
    @FXML
    public TextField TxtExperiencia;
    @FXML
    public Button btnAgregar;
    @FXML
    public Button btnRegresar;
    private final AgenciaCliente agenciaViajes = AgenciaCliente.getInstance();
    @FXML
    public CheckBox Espanol;
    @FXML
    public CheckBox Ingles;
    @FXML
    public CheckBox Portugues;
    @FXML
    public CheckBox Frances;
    @FXML
    public CheckBox Aleman;
    @FXML
    public TextField TxtIdentificacion;

    private AnchorPane panel;

    public void init(AnchorPane panel) {
        this.panel = panel;

    }
    public void regresar(){
        try
        {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnGestiGuias.fxml"));
            Pane nuevaVentana = loader.load();
            VtnGestiGuiasController gestiGuiasController=loader.getController();
            gestiGuiasController.init(panel);


            // Limpiar el contenido anterior y establecer el nuevo contenido
            panel.getChildren().clear();
            panel.getChildren().add(nuevaVentana);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
}


    public void agregarGuia() {
        ArrayList<String> lenguajes=new ArrayList<String>();
        if(Espanol.isSelected())
        {
            lenguajes.add(Espanol.getText());
        }

        if(Ingles.isSelected())
        {
            lenguajes.add(Ingles.getText());
        }

        if(Portugues.isSelected())
        {
            lenguajes.add(Portugues.getText());
        }

        if(Frances.isSelected())
        {
            lenguajes.add(Frances.getText());
        }

        if(Aleman.isSelected())
        {
            lenguajes.add(Aleman.getText());
        }

        try
        {
            agenciaViajes.agregarGuiaTuristico(TxtNombre.getText(),TxtIdentificacion.getText(),lenguajes,TxtExperiencia.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Se ha creado el nuevo guia.");
            alert.setHeaderText(null);
            alert.show();
            System.out.println(lenguajes.get(0));
        }
        catch (CampoObligatorioException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
        catch (IgualesException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }

}
