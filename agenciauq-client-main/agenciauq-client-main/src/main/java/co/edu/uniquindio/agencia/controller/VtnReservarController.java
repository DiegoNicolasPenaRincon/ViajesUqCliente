package co.edu.uniquindio.agencia.controller;

import co.edu.uniquindio.agencia.exceptions.CampoObligatorioException;
import co.edu.uniquindio.agencia.exceptions.MalaFechaException;
import co.edu.uniquindio.agencia.exceptions.ValorInvalidoException;
import co.edu.uniquindio.agencia.model.AgenciaCliente;
import co.edu.uniquindio.agencia.model.Clientes;
import co.edu.uniquindio.agencia.model.GuiasTuristicos;
import co.edu.uniquindio.agencia.model.PaqueteTuristico;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class VtnReservarController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    @FXML
    private DatePicker DateInicio;

    @FXML
    private Spinner<Integer> txtNumPersonas;

    @FXML
    private TextArea txtGuia;

    @FXML
    private ComboBox<String> boxGuia;

    @FXML
    private Label lblPrecio;

    private AnchorPane panelVentanas;
    private PaqueteTuristico paquete;
    private Clientes cliente;


    public void init(AnchorPane panelVentana, PaqueteTuristico paqueteSeleccionado, Clientes cliente) {
        this.cliente=cliente;
        this.panelVentanas=panelVentana;
        this.paquete=paqueteSeleccionado;

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, Integer.MAX_VALUE, 1); // Rango y valor inicial
        txtNumPersonas.setValueFactory(valueFactory);

        lblPrecio.setText(String.valueOf(paquete.getPrecio()));

        // Agregar un ChangeListener al Spinner
        txtNumPersonas.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                // Actualizar el texto del Label cuando cambia el valor del Spinner
                lblPrecio.setText(String.valueOf(paquete.getPrecio()*newValue));
            }
        });

        ObservableList<String> opciones = FXCollections.observableArrayList();
        opciones.add("No");
        for (GuiasTuristicos guia : agenciaCliente.getListaGuias()) {
            opciones.add(guia.getNombre());
        }
        boxGuia.setItems(opciones);
        boxGuia.getSelectionModel().select(0);
    }

    public void regresar(){
        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventanas/VtnPtesCliente.fxml"));
            Pane nuevaVentana = loader.load();
            VtnPtesClienteController ptesClienteController=loader.getController();
            ptesClienteController.init(panelVentanas, cliente);


            // Limpiar el contenido anterior y establecer el nuevo contenido
            panelVentanas.getChildren().clear();
            panelVentanas.getChildren().add(nuevaVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearReserva(){
        try{
        agenciaCliente.crearReserva(
                cliente,
                paquete,
                DateInicio.getValue(),
                String.valueOf(txtNumPersonas.getValue()),
                boxGuia.getValue(),
                lblPrecio.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Nueva Reserva Creada.");
            alert.setHeaderText(null);
            alert.show();
            Thread correoThread = new Thread(() -> {
                try {
                    enviarCorreo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            correoThread.start();
            regresar();
        }catch(CampoObligatorioException | MalaFechaException | ValorInvalidoException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void enviarCorreo(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Credenciales del remitente
        final String username = "santiagobv687@gmail.com";
        final String password = "jxpr ewfx cysj oaop";

        // Crear una sesión de correo con autenticación
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear un mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cliente.getCorreo()));
            message.setSubject("Reserva en Agencia de Viajes Uq");
            message.setText("Paquete Turistico: " +paquete.getNombre()+"\n"+
                    "Fecha de realización de la Reserva:"+ LocalDate.now()+"\n"+
                    "Fecha del viaje: "+ DateInicio.getValue()+"\n"+
                    "Guia Turistico:" +boxGuia.getValue()+"\n"+
                    "Precio Total: "+lblPrecio.getText());

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado con éxito.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }


}
