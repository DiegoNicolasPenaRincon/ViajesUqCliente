package co.edu.uniquindio.agencia.model;

import co.edu.uniquindio.agencia.exceptions.*;
import co.edu.uniquindio.agencia.socket.Mensaje;
import lombok.Getter;
import lombok.extern.java.Log;
import utils.Persistencia_Serializacion;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Log
public class AgenciaCliente {

    private static final String HOST="localhost";
    private static final int PUERTO=1234;

    private ArrayList<Clientes> listaClientes;
    @Getter
    private ArrayList<Administradores> listaAdministradores;
    @Getter
    private ArrayList<Destino> listaDestinos;
    @Getter
    private ArrayList<PaqueteTuristico> listaPaquetes;
    @Getter
    private ArrayList<GuiasTuristicos> listaGuias;
    @Getter
    private ArrayList<Reservas> listaReservas;
    private static final Logger LOGGER= Logger.getLogger(AgenciaCliente.class.getName());
    private static AgenciaCliente AgenciaCliente;
    public final String rutaClientes="src/main/resources/Persistencia/clientes.txt";
    public final String rutaAdministradores="src/main/resources/Persistencia/administradores.txt";
    public final String rutaDestinos="src/main/resources/Persistencia/destinos.ser";
    public final String rutaPaquetes="src/main/resources/Persistencia/paqueteTuristico.ser";
    public final String rutaGuias="src/main/resources/Persistencia/guiasTuristicos.ser";
    public final String rutaReservas="src/main/resources/Persistencia/reservas.ser";

    public AgenciaCliente()  {
        crearLogger();
        LOGGER.log( Level.INFO, "Se crea una nueva instancia de Agencia de Viajes." );
        this.listaClientes=new ArrayList<>();
        this.listaAdministradores=new ArrayList<>();
        this.listaDestinos=new ArrayList<>();
        this.listaPaquetes=new ArrayList<>();
        this.listaGuias=new ArrayList<>();
        this.listaReservas=new ArrayList<>();
        try{
        cargarClientes();
        LOGGER.log(Level.INFO, "Clientes cargados correctamente");
        cargarAdmins();
        LOGGER.log(Level.INFO, "Administradores cargados correctamente");
        cargarDestinos();
        cargarPaquetesTuristicos();
        cargarGuiasTuristicos();
        cargarReservas();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void crearLogger(){
        try{
            FileHandler fh= new FileHandler("logs.log", true);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        }catch(IOException e){
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    public static AgenciaCliente getInstance(){
        if(AgenciaCliente ==null){
            AgenciaCliente =new AgenciaCliente();
        }
        return AgenciaCliente;
    }

    public void cargarClientes() throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Mensaje.builder()
                    .tipo("cargarClientes").build());

            Object respuesta = in.readObject();

            if (respuesta instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) respuesta;

                if (!lista.isEmpty() && lista.get(0) instanceof Clientes) {
                    this.listaClientes = (ArrayList<Clientes>) lista;
                    System.out.println(listaClientes.get(0).getNombre());
                } else {
                    // Manejar un problema de tipo de respuesta inesperado
                    throw new RuntimeException("Respuesta inesperada del servidor");
                }
            } else {
                // Manejar un problema de tipo de respuesta inesperado
                throw new RuntimeException("Respuesta inesperada del servidor");
            }

        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void cargarAdmins() throws IOException {
        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Mensaje.builder().tipo("cargarAdmins").build());

            Object respuesta = in.readObject();

            System.out.println("Tipo de respuesta recibida: " + respuesta.getClass().getName());
            System.out.println("Contenido de la respuesta: " + respuesta);

            if (respuesta instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) respuesta;

                if (!lista.isEmpty() && lista.get(0) instanceof Administradores) {
                    this.listaAdministradores = (ArrayList<Administradores>) lista;
                    System.out.println(listaAdministradores.get(0).getNombre());
                } else {
                    // Manejar un problema de tipo de respuesta inesperado
                    throw new RuntimeException("Respuesta inesperada del servidor. Tipo de respuesta incorrecto");
                }
            } else {
                // Manejar un problema de tipo de respuesta inesperado
                throw new RuntimeException("Respuesta inesperada del servidor. Tipo de respuesta incorrecto");
            }

        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void cargarDestinos(){
        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Mensaje.builder().tipo("cargarDestinos").build());

            Object respuesta = in.readObject();

            System.out.println("Tipo de respuesta recibida: " + respuesta.getClass().getName());
            System.out.println("Contenido de la respuesta: " + respuesta);

            if (respuesta instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) respuesta;

                if (!lista.isEmpty() && lista.get(0) instanceof Destino) {
                    this.listaDestinos = (ArrayList<Destino>) lista;
                    System.out.println(listaDestinos.get(0).getNombre());
                } else {
                    // Manejar un problema de tipo de respuesta inesperado
                    throw new RuntimeException("Respuesta inesperada del servidor. Tipo de respuesta incorrecto");
                }
            } else {
                // Manejar un problema de tipo de respuesta inesperado
                throw new RuntimeException("Respuesta inesperada del servidor. Tipo de respuesta incorrecto");
            }

        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void cargarPaquetesTuristicos(){
        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Mensaje.builder().tipo("cargarPaquetesTuristicos").build());

            Object respuesta = in.readObject();

            System.out.println("Tipo de respuesta recibida: " + respuesta.getClass().getName());
            System.out.println("Contenido de la respuesta: " + respuesta);

            if (respuesta instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) respuesta;

                if (!lista.isEmpty() && lista.get(0) instanceof PaqueteTuristico) {
                    this.listaPaquetes = (ArrayList<PaqueteTuristico>) lista;
                    System.out.println(listaPaquetes.get(0).getNombre());
                } else {
                    // Manejar un problema de tipo de respuesta inesperado
                    throw new RuntimeException("Respuesta inesperada del servidor. Tipo de respuesta incorrecto");
                }
            } else {
                // Manejar un problema de tipo de respuesta inesperado
                throw new RuntimeException("Respuesta inesperada del servidor. Tipo de respuesta incorrecto");
            }

        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void cargarGuiasTuristicos() {
        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Mensaje.builder().tipo("cargarGuiasTuristicos").build());

            Object respuesta = in.readObject();

            System.out.println("Tipo de respuesta recibida: " + respuesta.getClass().getName());
            System.out.println("Contenido de la respuesta: " + respuesta);

            if (respuesta instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) respuesta;

                if (lista != null && !lista.isEmpty() && lista.get(0) instanceof GuiasTuristicos) {
                    this.listaGuias = (ArrayList<GuiasTuristicos>) lista;
                    System.out.println(listaGuias.get(0).getNombre());
                } else {
                    // Manejar un problema de tipo de respuesta inesperado o lista nula
                    System.out.println("La lista recibida es null o no contiene elementos válidos.");
                    this.listaGuias = new ArrayList<>(); // Asignar un ArrayList vacío
                }
            } else {
                // Manejar un problema de tipo de respuesta inesperado
                System.out.println("Respuesta inesperada del servidor. Tipo de respuesta incorrecto.");
                this.listaGuias = new ArrayList<>(); // Asignar un ArrayList vacío
            }

        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
            this.listaGuias = new ArrayList<>(); // Asignar un ArrayList vacío en caso de excepción
        }
    }

    public void cargarReservas(){
        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Mensaje.builder().tipo("cargarReservas").build());

            Object respuesta = in.readObject();

            System.out.println("Tipo de respuesta recibida: " + respuesta.getClass().getName());
            System.out.println("Contenido de la respuesta: " + respuesta);

            if (respuesta instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) respuesta;

                if (lista != null && !lista.isEmpty() && lista.get(0) instanceof Reservas) {
                    this.listaReservas = (ArrayList<Reservas>) lista;
                    System.out.println(listaReservas.get(0).getCliente().getNombre());
                } else {
                    // Manejar un problema de tipo de respuesta inesperado o lista nula
                    System.out.println("La lista recibida es null o no contiene elementos válidos.");
                    this.listaReservas = new ArrayList<>(); // Asignar un ArrayList vacío
                }
            } else {
                // Manejar un problema de tipo de respuesta inesperado
                System.out.println("Respuesta inesperada del servidor. Tipo de respuesta incorrecto.");
                this.listaReservas = new ArrayList<>(); // Asignar un ArrayList vacío
            }

        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
            this.listaReservas = new ArrayList<>(); // Asignar un ArrayList vacío en caso de excepción
        }
    }

    public Clientes registrarCliente(String nombre, String cedula, String contrasena, String telefono,
                                     String correo, String direccion)
            throws CampoObligatorioException, IgualesException, IOException {
        try (Socket socket = new Socket(HOST, PUERTO)) {
            // Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                // Se crea un cliente con los datos obtenidos desde la ventana
                Clientes cliente = Clientes.builder()
                        .nombre(nombre)
                        .cedula(cedula)
                        .contrasena(contrasena)
                        .telefono(telefono)
                        .correo(correo)
                        .direccion(direccion)
                        .build();

                // Se envía un mensaje al servidor con los datos de la petición
                out.writeObject(Mensaje.builder()
                        .tipo("registrarCliente")
                        .contenido(cliente)
                        .build());

                // Obtenemos la respuesta del servidor (objeto Cliente)
                Object respuesta = in.readObject();

                if (respuesta instanceof Clientes) {
                    return (Clientes) respuesta;
                } else {
                    // Manejar un problema de tipo de respuesta inesperado
                    throw new RuntimeException("Respuesta inesperada del servidor. Tipo de respuesta incorrecto");
                }

            } catch (Exception e) {
                log.severe(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }




    public String verificarInicio(String nombre, String contrasena, int indice, int indice2) {
        // Caso base: llegamos al final del ArrayList de clientes
        if (indice == listaClientes.size()) {
            // Caso base: llegamos al final del ArrayList de administradores
            if (indice2 == listaAdministradores.size()) {
                return "no";
            }

            // Verificar el nombre y la contraseña en el índice actual de administradores
            Administradores adminActual = listaAdministradores.get(indice2);
            if (adminActual.getNombre().equals(nombre) && adminActual.getContrasena().equals(contrasena)) {
                log.info("Se ha iniciado sesion como administrador");
                return "admin"; // Coinciden las credenciales
            } else {
                // Llamada recursiva para verificar en el siguiente índice de administradores
                return verificarInicio(nombre, contrasena, indice, indice2 + 1);
            }
        }

        // Verificar el nombre y la contraseña en el índice actual de clientes
        Clientes clienteActual = listaClientes.get(indice);
        if (clienteActual.getNombre().equals(nombre) && clienteActual.getContrasena().equals(contrasena)) {
            log.info("Se ha iniciado sesion como cliente");
            return "cliente"; // Coinciden las credenciales
        } else {
            // Llamada recursiva para verificar en el siguiente índice de clientes
            return verificarInicio(nombre, contrasena, indice + 1, indice2);
        }
    }

    public Clientes buscarCliente( String nombreUsuario, String contrasena, int indice) {
        if (indice >= listaClientes.size()) {
            return null; // No se encontró el cliente
        }

        Clientes cliente = listaClientes.get(indice);
        if (cliente.getNombre().equals(nombreUsuario) && cliente.getContrasena().equals(contrasena)) {
            return cliente;
        } else {
            return buscarCliente(nombreUsuario, contrasena, indice + 1);
        }
    }

    public int buscarIndiceCliente(String cedula, int indice){
        if (indice < listaClientes.size()) {
            if (listaClientes.get(indice).getCedula().equals(cedula)) {
                return indice; // Se encontró la cédula, retorna el índice del cliente
            } else {
                // No se encontró la cédula, continúa la búsqueda recursiva
                return buscarIndiceCliente(cedula, indice + 1);
            }
        } else {
            return -1; // No se encontró la cédula en la lista
        }
    }

    public Clientes obtenerCliente(String cedula, int index) {
        if (index >= listaClientes.size()) {
            return null; // No se encontró el cliente con la cédula especificada
        }
        Clientes clienteActual = listaClientes.get(index);

        if (clienteActual.getCedula().equals(cedula)) {
            return clienteActual; // Se encontró el cliente con la cédula especificada
        }

        // Llamada recursiva para buscar en el resto de la lista
        return obtenerCliente(cedula, index + 1);
    }


    // Método recursivo para modificar el cliente en el ArrayList
    public Clientes modificarCliente(String nuevoNombre, String cedula, String nuevaContrasena,
                                     String nuevoTelefono, String nuevoCorreo, String nuevaDireccion, int indice) throws CampoObligatorioException {
        if(nuevoNombre.isBlank() || nuevaContrasena.isBlank() || nuevoCorreo.isBlank() || nuevoTelefono.isBlank() || nuevaDireccion.isBlank() ||
                nuevoNombre==null || nuevaContrasena==null || nuevoCorreo==null || nuevoTelefono==null || nuevaDireccion==null){
            log.severe("Debe de ingresar todos los datos para registrar un nuevo usuario");
            throw new CampoObligatorioException("Debe completar todos los campos de información.");
        }

        if (indice < listaClientes.size()) {
            Clientes cliente = listaClientes.get(indice);
            if (cliente.getCedula().equals(cedula)) {
                // Se encontró al cliente, actualizar sus atributos
                cliente.setNombre(nuevoNombre);
                cliente.setContrasena(nuevaContrasena);
                cliente.setCorreo(nuevoCorreo);
                cliente.setTelefono(nuevoTelefono);
                cliente.setDireccion(nuevaDireccion);
                return cliente; //
            } else {
                return modificarCliente(nuevoNombre, cedula, nuevaContrasena, nuevoTelefono, nuevoCorreo, nuevaDireccion, indice + 1);
            }
        }
        return null;
    }

    // Método para actualizar el archivo de clientes
    public void actualizarArchivoClientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaClientes))) {
            for (Clientes cliente : listaClientes) {
                // Escribir la información del cliente en el archivo
                writer.write(cliente.getNombre() + "," + cliente.getCedula() + ","+ cliente.getContrasena()+ "," +
                        cliente.getTelefono() + "," + cliente.getCorreo() + "," + cliente.getDireccion());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarPaquete(PaqueteTuristico paqueteSeleccionado) throws SeleccionarElementoException {
        if(paqueteSeleccionado!=null){
            return true;
        }
        LOGGER.log( Level.WARNING, "No se ha seleccionado algun elemento de la tabla" );
        throw new SeleccionarElementoException("Debe seleccionar un elemento de la tabla");
    }

    public boolean verificarDestino(Destino destino) throws SeleccionarElementoException {
        if(destino!=null){
            return true;
        }
        LOGGER.log( Level.WARNING, "No se ha seleccionado algun elemento de la tabla" );
        throw new SeleccionarElementoException("Debe seleccionar un elemento de la tabla");
    }

    public boolean verificarReserva(Reservas reserva) throws SeleccionarElementoException {
        if(reserva!=null){
            return true;
        }
        LOGGER.log( Level.WARNING, "No se ha seleccionado algun elemento de la tabla" );
        throw new SeleccionarElementoException("Debe seleccionar un elemento de la tabla");
    }

    public boolean verificarEstado(Reservas reserva) throws NoHayObjetoException {
        if(reserva.getEstado()!=EstadoReserva.CANCELADA){
            return true;
        }
        log.info("La reserva ya se encuentra cancelada");
        throw new NoHayObjetoException("La reserva ya se encuentra cancelada");
    }

    public boolean verificarDestinoEnLista(Destino destino, ArrayList<Destino> destinos){
        if (destinos.contains(destino)) {
            return false;
        } else {
            return true;
        }
    }

    public void verificarURLS(String texto1, String texto2, String texto3) throws CampoObligatorioException {
        if(texto1.isBlank() || texto2.isBlank() || texto3.isBlank()){
            log.severe("Debe subir 3 fotos del destino");
            throw new CampoObligatorioException("Debe subir 3 fotos del destino");
        }
    }

    public void crearReserva(Clientes cliente, PaqueteTuristico paquete,
                             LocalDate fechaViaje, String numPersonas, String guia, String precioTotal) throws CampoObligatorioException, MalaFechaException, ValorInvalidoException, FileNotFoundException {
        if (cliente == null || paquete == null || fechaViaje == null || numPersonas == null || guia==null) {
            log.warning("Hay datos incompletos para hacer la reserva");
            throw new CampoObligatorioException("Hay datos incompletos para hacer la reserva");
        }
        LocalDate fechaActual=LocalDate.now();
        if(fechaViaje.isBefore(fechaActual)){
            log.warning("El viaje no puede ser en una fecha anterior a la actual");
            throw new MalaFechaException("El viaje no puede ser en una fecha anterior a la actual");
        }
        verificarFechasConPaquete(paquete.getFechaFin(), paquete.getFechaInicio(), fechaViaje);


        if(!numPersonas.matches("\\d+")){
            log.warning("El número de personas debe ser un valor numerico");
            throw new ValorInvalidoException("El número de personas debe ser un valor numerico");
        }
        GuiasTuristicos guiaTuristico = null;
        if(!guia.equals("No")){
             guiaTuristico=buscarGuiaTuristico(guia, 0);
        }

        int personas=Integer.parseInt(numPersonas);
        double precio=Double.parseDouble(precioTotal);

        Reservas reserva=Reservas.builder()
                .cliente(cliente)
                .paquete(paquete)
                .fechaSolicitud(fechaActual)
                .fechaViaje(fechaViaje)
                .numPersonas(personas)
                .guiaTuristico(guiaTuristico)
                .estado(EstadoReserva.PENDIENTE)
                .precioTotal(precio)
                .build();
        listaReservas.add(reserva);
        Persistencia_Serializacion.serializarObjetoXML2(rutaReservas,listaReservas);
    }

    public void cancelarReserva(Reservas reserva) throws FileNotFoundException, NoHayObjetoException {
        // Buscar la reserva en el ArrayList
        int index = listaReservas.indexOf(reserva);

        if (index != -1) {
            // Modificar el estado de la reserva a "CANCELADA"
            Reservas reservaEncontrada = listaReservas.get(index);
            reservaEncontrada.setEstado(EstadoReserva.CANCELADA);

            log.info("Reserva Cancelada");
            Persistencia_Serializacion.serializarObjetoXML2(rutaReservas,listaReservas);
        } else {
            log.warning("Reserva no encontrada");
            throw new NoHayObjetoException("No se ha encontrado la reserva");
        }
    }

    public void verificarFechasConPaquete(LocalDate fechaFin, LocalDate fechaInicio, LocalDate fechaViaje) throws MalaFechaException {
        if (fechaViaje.isAfter(fechaInicio) || fechaViaje.isEqual(fechaInicio)) {
            if (fechaViaje.isBefore(fechaFin) || fechaViaje.isEqual(fechaFin)) {
                log.info("Fecha dentro del rango");
            } else {
                log.warning("La fecha seleccionada es despues del final del paquete.");
                throw new MalaFechaException("La fecha seleccionada es despues del final del paquete.");
            }
        } else {
            log.warning("La fecha seleccionada es antes del inicio del paquete.");
            throw new MalaFechaException("La fecha seleccionada es antes del inicio del paquete.");
        }
    }

    public GuiasTuristicos buscarGuiaTuristico(String nombreGuia, int indice){
        if (indice >= 0 && indice < listaGuias.size()) {
            GuiasTuristicos guia = listaGuias.get(indice);
            if (guia.getNombre().equals(nombreGuia)) {
                // Se encontró el guía con el nombre dado
                return guia;
            } else {
                // Llamada recursiva para buscar en el siguiente índice
                return buscarGuiaTuristico(nombreGuia, indice + 1);
            }
        } else {
            // No se encontró ningún guía con el nombre dado
            return null;
        }
    }

    //Metodos relacionados con los adminsitradores

    //Metodos relacionados con los destinos

    /**
     * Agrega un nuevo destino a la lista de destinos disponibles en nuestra agencia de biajes
     * @param nombre nombre del destino que se va a agregar
     * @param ciudad ciudad del destino que se va a agregar
     * @param descripcion descripcion del destino que se va a agregar
     * @param rutasImagenes las rutas donde se encuentran las imagenes de los destinos
     * @param clima clima del destino
     */
    public void agregarDestino(String nombre,String ciudad,String descripcion,ArrayList<String> rutasImagenes,Clima clima) throws IgualesException, CampoObligatorioException, FileNotFoundException {
        validarVacio(nombre,"El destino debe tener un nombre");
        validarVacio(ciudad,"El destino debe de localizarse en algun lugar");
        validarVacio(descripcion,"El destino debe de tener una descripcion");
        if(rutasImagenes==null)
        {
            throw new CampoObligatorioException("El destino debe de tener por lo menos una imagen que lo caracterice");
        }

        if(clima==null)
        {
            throw new CampoObligatorioException("Debe especificar el clima del destino");
        }

        validarDestinosIguales(nombre,0,ciudad);
        Destino destino=Destino.builder()
                .nombre(nombre)
                .ciudad(ciudad)
                .descripcion(descripcion)
                .rutasImagenes(rutasImagenes)
                .clima(clima)
                .build();
        listaDestinos.add(destino);
        Persistencia_Serializacion.serializarObjetoXML(rutaDestinos,listaDestinos);
    }

    /**
     *
     * @param destino que se va a eliminar
     */
    public void eliminarDestino(Destino destino) throws NoHayObjetoException, FileNotFoundException {
        if(listaDestinos.contains(destino))
        {
            listaDestinos.remove(destino);
        }
        else
        {
            throw new NoHayObjetoException("El destino ya no se encuentra en nuestra base de datos");
        }

        Persistencia_Serializacion.serializarObjetoXML(rutaDestinos,listaDestinos);
    }

    public void eliminarDestinoPaquetes(int longitud,Destino destino) throws FileNotFoundException {
        if(longitud<listaPaquetes.size())
        {
            if(listaPaquetes.get(longitud).getDestinos().contains(destino))
            {
                listaPaquetes.get(longitud).getDestinos().remove(destino);
                mensajeInformativo("el destino ha sido eliminado");
            }
            else
            {
                eliminarDestinoPaquetes(longitud+1,destino);
            }
        }
    }

    public void agregarDestinoPaquete(int longitud,Destino destino,PaqueteTuristico paquete) throws FileNotFoundException {
        if(longitud<listaPaquetes.size())
        {
            if(paquete.equals(listaPaquetes.get(longitud)))
            {
                if(!paquete.getDestinos().contains(destino))
                {
                    listaPaquetes.get(longitud).getDestinos().add(destino);
                    longitud=listaPaquetes.size();
                }
                else
                {
                    longitud=listaPaquetes.size();
                }
            }
            else
            {
                agregarDestinoPaquete(longitud+1,destino,paquete);
            }
        }
    }

    /**
     * Modifica un destino
     * @param longitud permite recorrer la lista de destinos
     * @param descripcion descripcion nueva del destino
     * @param rutasImagenes nuevas imagenes del destino o imagenes que se borraran del destino
     * @param clima nuevo clima del destino
     * @param nombreNuevo nuevo nombre del destino
     * @param ciudadNueva nueva ciudad del destino
     */
    public void modificarDestinos(Destino destino,int longitud,String descripcion,ArrayList<String> rutasImagenes,Clima clima,String nombreNuevo,String ciudadNueva,String agregarOeliminarImagenes) throws FileNotFoundException {
        if (longitud<listaDestinos.size())
        {
            if(destino.equals(listaDestinos.get(longitud)))
            {
                if(descripcion!=null&&!descripcion.isEmpty())
                {
                    listaDestinos.get(longitud).setDescripcion(descripcion);
                }
                if(clima!=null)
                {
                    listaDestinos.get(longitud).setClima(clima);
                }
                if(nombreNuevo!=null&&!nombreNuevo.isEmpty())
                {
                    listaDestinos.get(longitud).setNombre(nombreNuevo);
                }
                if(ciudadNueva!=null&&!ciudadNueva.isEmpty())
                {
                    listaDestinos.get(longitud).setCiudad(ciudadNueva);
                }

                if (agregarOeliminarImagenes!=null)
                {
                    if(agregarOeliminarImagenes.equals("Agregar"))
                    {
                        if(rutasImagenes.size()>0)
                        {
                            listaDestinos.get(longitud).getRutasImagenes().addAll(rutasImagenes);
                        }
                    }
                    if(agregarOeliminarImagenes.equals("Eliminar"))
                    {
                        if(rutasImagenes.size()>0)
                        {
                            listaDestinos.get(longitud).getRutasImagenes().removeAll(rutasImagenes);
                        }
                    }
                }

                modificarDestinoPaquete(0,listaDestinos.get(longitud),destino,0);
                longitud=listaDestinos.size();
            }
            else
            {
                modificarDestinos(destino,longitud+1,descripcion,rutasImagenes,clima,nombreNuevo,ciudadNueva,agregarOeliminarImagenes);
            }

            Persistencia_Serializacion.serializarObjetoXML(rutaDestinos,listaDestinos);
            Persistencia_Serializacion.serializarObjetoXMLConLocalDate(rutaPaquetes,listaPaquetes);
        }
    }

    public void modificarDestinoPaquete(int longitud, Destino destino,Destino destino1,int longitud2) throws FileNotFoundException {
        if(longitud<listaPaquetes.size())
        {
            if(longitud2<listaPaquetes.get(longitud).getDestinos().size())
            {
                if(destino1.equals(listaPaquetes.get(longitud).getDestinos().get(longitud2)))
                {
                    int indice=listaPaquetes.get(longitud).getDestinos().indexOf(destino1);
                    listaPaquetes.get(longitud).getDestinos().set(indice,destino);
                }
                else
                {
                    modificarDestinoPaquete(longitud,destino,destino1,longitud2+1);
                }
            }
            else
            {
                modificarDestinoPaquete(longitud+1,destino,destino1,longitud2=0);
            }
        }
    }


    /**
     * Permite llenar un radioButton o un comboBox con las imagenes que tienen un destino, esto se hace para poder borrar estas imagenes posteriormente
     * @param destino al que se le van a borrar las imagenes
     * @param imagenesBorrar lista de imagenes que seran evaluadas por el administrador para posteriormente este decida si borrarlas o no
     * @param longitud variable que permite recorrer la lista de imagenes del destino
     * @return retorna la lista de iamgenes disponibles para ser borradas de un destino
     */
    public ArrayList<String> agregarImagenesParaBorrar(Destino destino,ArrayList<String> imagenesBorrar,int longitud) {
        if(longitud<destino.getRutasImagenes().size())
        {
            imagenesBorrar.add(destino.getRutasImagenes().get(longitud));
            return agregarImagenesParaBorrar(destino,imagenesBorrar,longitud+1);
        }
        else
        {
            return imagenesBorrar;
        }

    }

    //Metodos relacionados con los paquetes turisticos

    public void crearPaquete(String nombre, String duracion, String precio, String cupoMaximo, String serviciosAdicionales, LocalDate fechaIncio, LocalDate fechaFin, ArrayList<Destino> destinos) throws CampoObligatorioException, IgualesException, MalaFechaException, FileNotFoundException, ValorInvalidoException, SeleccionarElementoException {
        validarVacio(nombre,"El paquete debe tener un nombre");
        validarVacio(duracion,"Debe indicar la duracion del paquete");
        validarVacio(serviciosAdicionales,"Debe especificar si el paquete incluye servicios adicionales");
        validarVacio(precio,"El paquete debe contener un precio ");
        validarVacio(cupoMaximo,"Debe especificar el cupo maximo del paquete");
        validarFechas(fechaIncio,fechaFin);
        if (!duracion.matches("^[0-9]*$")) {
            LOGGER.log(Level.WARNING, "La duración del viaje debe ser un número");
            throw new ValorInvalidoException("La duración del viaje debe ser un número");

        }
        if (!precio.matches("^[0-9]*$")) {
            LOGGER.log(Level.WARNING, "El precio debe ser un número, sin usar simbolos o letras");
            throw new ValorInvalidoException("El precio debe ser un número, sin usar simbolos o letras");
        }
        if (!cupoMaximo.matches("^[0-9]*$")) {
            LOGGER.log(Level.WARNING, "El cupo Maximo debe ser un número");
            throw new ValorInvalidoException("El cupo Maximo debe ser un número");
        }
        if(destinos==null){
            log.warning("No se han agregado destinos al paquete");
            throw new CampoObligatorioException("No se han agregado destinos al paquete");
        }

        buscarPaquetesIgualesPorNombre(nombre,0);
        int duracionApoyo=Integer.parseInt(duracion);
        double precioApoyo=Double.parseDouble(precio);
        int cupoMaximoApoyo=Integer.parseInt(cupoMaximo);
        PaqueteTuristico paquete;
        paquete= PaqueteTuristico.builder()
                .nombre(nombre)
                .servicios(serviciosAdicionales)
                .precio(precioApoyo)
                .cupoMaximo(cupoMaximoApoyo)
                .fechaInicio(fechaIncio)
                .fechaFin(fechaFin)
                .duracion(duracionApoyo)
                .destinos(destinos)
                .build();
        listaPaquetes.add(paquete);

        Persistencia_Serializacion.serializarObjetoXML2(rutaPaquetes,listaPaquetes);
    }

    public PaqueteTuristico modificarPaquete(String nombreAntiguo, String nuevoNombre, String nuevoServicios, String nuevoPrecio,
                                 LocalDate nuevoInicio, LocalDate nuevoFinal, String nuevoCupo, String nuevaDuracion,
                                 ArrayList<Destino> nuevoDestinos, int indice) throws CampoObligatorioException, MalaFechaException, ValorInvalidoException {
        validarVacio(nuevoNombre,"El paquete debe tener un nombre");
        validarVacio(nuevaDuracion,"Debe indicar la duracion del paquete");
        validarVacio(nuevoServicios,"Debe especificar si el paquete incluye servicios adicionales");
        validarVacio(nuevoPrecio,"El paquete debe contener un precio ");
        validarVacio(nuevoCupo,"Debe especificar el cupo maximo del paquete");
        validarFechas(nuevoInicio,nuevoFinal);
        if (!nuevaDuracion.matches("^[0-9]*$")) {
            LOGGER.log(Level.WARNING, "La duración del viaje debe ser un número");
            throw new ValorInvalidoException("La duración del viaje debe ser un número");

        }
        if (!nuevoPrecio.matches("^[0-9]*$")) {
            LOGGER.log(Level.WARNING, "El precio debe ser un número, sin usar simbolos o letras");
            throw new ValorInvalidoException("El precio debe ser un número, sin usar simbolos o letras");
        }
        if (!nuevoCupo.matches("^[0-9]*$")) {
            LOGGER.log(Level.WARNING, "El cupo Maximo debe ser un número");
            throw new ValorInvalidoException("El cupo Maximo debe ser un número");
        }
        if(nuevoDestinos==null){
            log.warning("No se han agregado destinos al paquete");
            throw new CampoObligatorioException("No se han agregado destinos al paquete");
        }
        if (indice < listaClientes.size()) {
            PaqueteTuristico paqueteTuristico = listaPaquetes.get(indice);
            if (paqueteTuristico.getNombre().equals(nombreAntiguo)) {
                // Se encontró al cliente, actualizar sus atributos
                paqueteTuristico.setNombre(nuevoNombre);
                paqueteTuristico.setPrecio(Double.parseDouble(nuevoPrecio));
                paqueteTuristico.setCupoMaximo(Integer.parseInt(nuevoCupo));
                paqueteTuristico.setDuracion(Integer.parseInt(nuevaDuracion));
                paqueteTuristico.setFechaInicio(nuevoInicio);
                paqueteTuristico.setFechaFin(nuevoFinal);
                paqueteTuristico.getDestinos().clear();
                paqueteTuristico.getDestinos().addAll(nuevoDestinos);
                return paqueteTuristico; //
            }
            else
            {
                return modificarPaquete(nombreAntiguo, nuevoNombre, nuevoServicios, nuevoPrecio,
                        nuevoInicio, nuevoFinal, nuevoCupo, nuevaDuracion, nuevoDestinos, indice + 1);
            }
        }
        return null;
    }


    public ArrayList<Destino> mostrarDestinosPaquete(int longitud,PaqueteTuristico paquete,ArrayList<Destino> destinos) {
        if(longitud<listaDestinos.size())
        {
            if(!paquete.getDestinos().contains(listaDestinos.get(longitud)))
            {
                destinos.add(listaDestinos.get(longitud));
                return mostrarDestinosPaquete(longitud+1,paquete,destinos);
            }
            else
            {
                return mostrarDestinosPaquete(longitud+1,paquete,destinos);
            }
        }
        else
        {
            return destinos;
        }
    }

    public void modificarPaquete2(int longitud,PaqueteTuristico paquete,String nuevoNombre,String nuevosServicios,String nuevaDuracion,String nuevoPrecio,String nuevoCupoMaximo,LocalDate nuevoInicio,LocalDate nuevoFinal) throws FileNotFoundException, MalaFechaException, NegativosException {
        validarNumerosNegativos(Integer.parseInt(nuevaDuracion),"la duracion no puede ser negativa");
        if(longitud<listaPaquetes.size())
        {
            if (paquete.equals(listaPaquetes.get(longitud)))
            {
                if(nuevoNombre!=null&&!nuevoNombre.isEmpty())
                {
                    listaPaquetes.get(longitud).setNombre(nuevoNombre);
                    System.out.println(listaPaquetes.get(longitud).getNombre());
                }

                if(nuevosServicios!=null&&!nuevosServicios.isEmpty())
                {
                    listaPaquetes.get(longitud).setServicios(nuevosServicios);
                }

                if(nuevaDuracion==null&&!nuevaDuracion.isEmpty())
                {
                    listaPaquetes.get(longitud).setDuracion(Integer.parseInt(nuevaDuracion));
                }

                if(nuevoPrecio!=null&&!nuevoPrecio.isEmpty())
                {
                    validarNumerosNegativos(Integer.parseInt(nuevoPrecio),"el precio no puede ser menor que 0");
                    listaPaquetes.get(longitud).setPrecio(Double.parseDouble(nuevoPrecio));
                }

                if(nuevoCupoMaximo!=null&&!nuevoCupoMaximo.isEmpty())
                {
                    validarNumerosNegativos(Integer.parseInt(nuevoCupoMaximo),"el cupo maximo no puede manejar valores engativos");
                    listaPaquetes.get(longitud).setCupoMaximo(Integer.parseInt(nuevoCupoMaximo));
                }

                if(nuevoInicio!=null&&nuevoFinal==null)
                {
                    validarFechas(nuevoInicio,listaPaquetes.get(longitud).getFechaFin());
                    listaPaquetes.get(longitud).setFechaInicio(nuevoInicio);
                }

                if(nuevoFinal!=null&&nuevoInicio==null)
                {
                    validarFechas(listaPaquetes.get(longitud).getFechaInicio(),nuevoFinal);
                    listaPaquetes.get(longitud).setFechaFin(nuevoFinal);
                }

                if(nuevoFinal!=null&&nuevoInicio!=null)
                {
                    validarFechas(nuevoInicio,nuevoFinal);
                    listaPaquetes.get(longitud).setFechaInicio(nuevoInicio);
                    listaPaquetes.get(longitud).setFechaFin(nuevoFinal);
                }

                longitud=listaPaquetes.size();
            }
            else
            {
                modificarPaquete2(longitud+1,paquete,nuevoNombre,nuevosServicios,nuevaDuracion,nuevoPrecio,nuevoCupoMaximo,nuevoInicio,nuevoFinal);
            }
        }
        Persistencia_Serializacion.serializarObjetoXMLConLocalDate(rutaPaquetes,listaPaquetes);
    }

    /**
     *Elimina un paquete turistico
     */
    public void eliminarPaqueteTuristico(PaqueteTuristico paquete) throws FileNotFoundException {
       if(listaPaquetes.contains(paquete))
       {
           listaPaquetes.remove(paquete);
       }

       Persistencia_Serializacion.serializarObjetoXMLConLocalDate(rutaPaquetes,listaPaquetes);
    }

    //Metodos referentes a los guias turisticos

    /**
     * Este metodo agrega un nuevo guia turistico
     * @param nombre nombre del guia
     * @param identificacion identificacion del guia
     * @param lenguajes lenguajes que puede hablar el guia
     * @param experiencia experiencia que tiene el guia
     */
    public void agregarGuiaTuristico(String nombre,String identificacion,ArrayList<String> lenguajes,String experiencia) throws CampoObligatorioException, IgualesException {
        validarVacio(nombre,"El guia debe tener un nombre");
        validarVacio(identificacion,"El guia debe tener una identificacion");
        if(lenguajes.size()<1)
        {
            throw new CampoObligatorioException("El guia debe conocer como minimo un lenguaje");
        }
        validarVacio(experiencia,"Debe especificar la experiencia que posee el guia");
        buscarGuiasIguales(identificacion,0);
        GuiasTuristicos guia=GuiasTuristicos.builder().nombre(nombre).identificacion(identificacion).lenguajes(lenguajes).experiencia(experiencia).build();

    }

    /**
     * Metodo que elimina un guia turistico
     * @param identificacion identificacion del guia
     * @param longitud longitud variable utilizada para buscar el guia
     */
    public void eliminarGuiaTuristico(String identificacion,int longitud) {
        if(longitud<listaGuias.size())
        {
            if(identificacion.equals(listaGuias.get(longitud).getIdentificacion()))
            {
                listaGuias.remove(listaGuias.get(longitud));
            }
            else
            {
                eliminarGuiaTuristico(identificacion,longitud+1);
            }
        }
    }

    /**
     * modifica los datos de un guia turistico
     * @param nombre nombre bnuevo
     * @param identificacion identificacion nueva guia
     * @param experiencia experiencia nueva guia
     * @param guia guia al cual se le va a modificar la informacion
     * @param longitud variable utilziada para buscar al guia
     * @param agregarLenguaje permite identificar si se desea agregar un nuevo lenguaje al guia
     * @param eliminarLenguaje permite identificar si se desea eliminar un nuevo lenguaje al guia
     * @param lenguajes lenguajes que va a hablar el guia
     */
    public void modificarGuia(String nombre,String identificacion,String experiencia,GuiasTuristicos guia,int longitud,boolean agregarLenguaje,boolean eliminarLenguaje,ArrayList<String> lenguajes)  {
        if(longitud<listaGuias.size())
        {
            if(guia.equals(listaGuias.get(longitud)))
            {
                if(nombre!=null||!nombre.isEmpty())
                {
                    listaGuias.get(longitud).setNombre(nombre);
                }
                if(identificacion!=null||identificacion.isEmpty())
                {
                    listaGuias.get(longitud).setIdentificacion(identificacion);
                }
                if(experiencia!=null||experiencia.isEmpty())
                {
                    listaGuias.get(longitud).setExperiencia(experiencia);
                }

                if(agregarLenguaje==true)
                {
                    if(lenguajes.size()>0)
                    {
                        agregarLenguajes(lenguajes,0,listaGuias.get(longitud));
                    }
                }

                if(eliminarLenguaje==true)
                {
                    if(lenguajes.size()>0)
                    {
                        eliminarLenguajes(lenguajes,0,listaGuias.get(longitud));
                    }
                }
            }
            else
            {
                modificarGuia(nombre,identificacion,experiencia,guia,longitud+1,agregarLenguaje,eliminarLenguaje,lenguajes);
            }
        }
    }

    /**
     * agrega un lenguaje o lenguajes al guia
     * @param lenguajes Arraylist con el lenguaje o los lenguajes que se le van a agregar al guia
     * @param longitud variable que recorre el ArrayList de lenguajes
     * @param guia al que se le agregaran los nuevos lenguajes
     */
    public void agregarLenguajes(ArrayList<String> lenguajes,int longitud,GuiasTuristicos guia) {
        if(longitud<lenguajes.size())
        {
            guia.getLenguajes().add(lenguajes.get(longitud));
            Set<String> hashSet = new HashSet<String>(guia.getLenguajes());
            guia.getLenguajes().clear();
            guia.getLenguajes().addAll(hashSet);
        }
    }

    /**
     * elimina un lenguaje del guia
     * @param lenguajes Arraylist con el lenguaje o los lenguajes que se le van a eliminar al guia
     * @param longitud variable que recorre el ArrayList de lenguajes
     * @param guia al que se le elminaran los lenguajes
     */
    public void eliminarLenguajes(ArrayList<String> lenguajes,int longitud,GuiasTuristicos guia) {
        if(longitud<lenguajes.size())
        {
            guia.getLenguajes().remove(lenguajes.get(longitud));
        }
    }

    /**
     * metodo que permite agregar los lenguajes que se pueden eliminar de un guia
     * @param guia el guia al cual se le va a eliminar el lenguaje
     * @return retorna arreglo para que pueda ser utilizado por un radioButton
     */
    public ArrayList<String> mostrarLenguajesDisponiblesEliminarGuia (GuiasTuristicos guia) {
        ArrayList<String> arreglo=guia.getLenguajes();
        return arreglo;
    }

    /**
     * metodo que muestra los lenguajes diponibles que se le pueden agregar a un guia
     * @param arreglo donde se van a incluir los idiomas para despues ser mostrados por un comboBox
     * @param guia al cual se le agregaran los lenguajes que tiene
     * @param longitud sirve para recorrer el atributo lenguajes del guia
     * @return se retorna a el mismo y arreglo
     */
    public ArrayList<String> mostrarLenguajesDisponiblesAgregar(ArrayList<String> arreglo,GuiasTuristicos guia,int longitud) {
        if(longitud<guia.getLenguajes().size())
        {
            if(!guia.getLenguajes().contains(GuiasTuristicos.lenguajesDisponibles.get(longitud)))
            {
                arreglo.add(GuiasTuristicos.lenguajesDisponibles.get(longitud));
                return mostrarLenguajesDisponiblesAgregar(arreglo,guia,longitud+1);
            }
            else
            {
                return mostrarLenguajesDisponiblesAgregar(arreglo,guia,longitud+1);
            }
        }
        else
        {
            return arreglo;
        }
    }

    /**
     * metodo que agrega un nuevo lenguaje a la lista de lenguajes disponibles que puede tener un guia turistico
     * @param lenguaje que se va a gregar
     * @throws FileNotFoundException excepcion que lanza si no encuentra el archivo lenguajes.txt
     */
    public void agregarLenguaje(String lenguaje) throws FileNotFoundException {
        if(!GuiasTuristicos.lenguajesDisponibles.contains(lenguaje))
        {
            GuiasTuristicos.lenguajesDisponibles.add(lenguaje);
            Persistencia_Serializacion.serializarObjetoXML("/src/main/resources/Persistencia/lenguajes.txt",GuiasTuristicos.lenguajesDisponibles);
        }
    }

    //Validaciones

    /**
     * Valida si hay dos destinos iguales
     * @param nombre del nuevo destino
     * @param longitud sirve para recorrer la lista de destinos en ViajesUq
     * @param ciudad del nuevo destino
     * @throws IgualesException lanza la excepcion si el destino ya se encuentra registrado
     */
    public void validarDestinosIguales(String nombre,int longitud,String ciudad) throws IgualesException {
        if(longitud<listaDestinos.size())
        {
            if(nombre.equals(listaDestinos.get(longitud).getNombre())&&ciudad.equals(listaDestinos.get(longitud).getCiudad()))
            {
                throw new IgualesException("Ese destino ya se encuentra registrado.");
            }
            else
            {
                validarDestinosIguales(nombre,longitud+1,ciudad);
            }
        }
    }

    /**
     * Metodo que busca si hay paquetes iguales, teniendo en cuenta el nombre
     * @param nombre nombre del paquete
     * @param longitud variable que sirve como contador
     * @throws IgualesException excepcion que lanza si encuentra que dos destinos son iguales
     */
    public void buscarPaquetesIgualesPorNombre(String nombre,int longitud) throws IgualesException {
        if(longitud<listaPaquetes.size())
        {
            if(nombre.equals(listaPaquetes.get(longitud).getNombre()))
            {
                throw new IgualesException("Ya existe un paquete con ese nombre");
            }
            else
            {
                buscarPaquetesIgualesPorNombre(nombre,longitud+1);
            }
        }
    }

    /**
     * busca si hay guias que tengan la misma identificacion
     * @param identificacion del guia
     * @param longitud variable que permite recorre el Arraylist de guias
     * @throws IgualesException la excepcion que se lanza si encuentra un guia con la misma identificacion
     */
    public void buscarGuiasIguales(String identificacion,int longitud) throws IgualesException {
        if(longitud<listaGuias.size())
        {
            if(identificacion.equals(listaGuias.get(longitud).getIdentificacion()))
            {
                throw new IgualesException("Es imposible que hayan dos guias turisticos con la misma informacion personal");
            }
            else
            {
                buscarGuiasIguales(identificacion,longitud+1);
            }
        }
    }

//Validaciones

    /**
     * Metodo universal que sirve para validar los espacioes vacios de los datos que se llegan de las interfaces
     * @param cualquiera cualquier String
     * @param msg el mensaje que va a mostrar la excepcion, ejemplo, debe ingresar su numero de cedula
     */
    public void validarVacio(String cualquiera,String msg) throws CampoObligatorioException {
        if(cualquiera == null|| cualquiera.isEmpty())
        {
            throw new CampoObligatorioException(msg);
        }
    }

    /**
     *
     * @param n numero a validar
     * @param msg el mensaje que lanzara la excepcion
     * @throws NegativosException la excepcion
     */
    public void validarNumerosNegativos(int n,String msg) throws NegativosException {
        if(n<0)
        {
            throw new NegativosException(msg);
        }
    }

    public static void validarFechas(LocalDate fechaInicio,LocalDate fechaFin) throws MalaFechaException {
        if(fechaInicio.isAfter(fechaFin))
        {
            log.warning("La fecha inicial no puede ser antes que la fecha final");
            throw new MalaFechaException("La fecha inicial no puede ser antes que la fecha final ");
        }
    }

    //Loggers

    /**
     * muestra un log informativo
     * @param msg mensaje que se guardara en el archivo log
     */
    public  void mensajeInformativo(String msg) {
        try
        {
            LOGGER.log(Level.INFO,msg);
        }
        catch (SecurityException e)
        {
            LOGGER.log(Level.SEVERE,e.getMessage());
        }

    }

    /**
     * muestra un log de alerta
     * @param msg mensaje que se guardara en el archivo log
     */
    public void mensajeAlerta(String msg) {
        try
        {
            LOGGER.log(Level.WARNING,msg);
        }
        catch (SecurityException e)
        {
            LOGGER.log(Level.SEVERE,e.getMessage());
        }

    }

    /**
     * muestra un log severo
     * @param msg mensaje que se guarda en el archivo log
     */
    public void mensajeSevero(String msg) {
        try
        {
            LOGGER.log(Level.SEVERE,msg);
        }
        catch (SecurityException e)
        {
            LOGGER.log(Level.SEVERE,e.getMessage());
        }

    }



}