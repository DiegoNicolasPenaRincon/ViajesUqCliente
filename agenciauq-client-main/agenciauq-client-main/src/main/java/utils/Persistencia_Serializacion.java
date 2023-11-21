package utils;

import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
public class Persistencia_Serializacion implements Serializable {

    /**
     * Permite leer un archivo desde una ruta específica mediante Scanner
     * @param ruta Ruta a leer
     * @return Lista de String por cada línea del archivo
     * @throws IOException
     */
    public static ArrayList<String> leerArchivoScanner(String ruta) throws IOException{

        ArrayList<String> lista = new ArrayList<>();
        Scanner sc = new Scanner(new File(ruta));

        while(sc.hasNextLine()) {
            lista.add(sc.nextLine());
        }

        sc.close();

        return lista;
    }

    /**
     * Permite leer un archivo desde una ruta específica mediante BufferedReader
     * @param ruta Ruta a leer
     * @return Lista de String por cada línea del archivo
     * @throws IOException
     */
    public static ArrayList<String> leerArchivoBufferedReader(String ruta) throws IOException{

        ArrayList<String> lista = new ArrayList<>();
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        while( ( linea = br.readLine() )!=null ) {
            lista.add(linea);
        }

        br.close();
        fr.close();

        return lista;
    }

    /**
     * Escribe datos en un archivo de texo
     * @param ruta Ruta donde se va a crear el archivo
     * @param lista Datos que se escriben en el archivo
     * @throws IOException
     */
    public static void escribirArchivoFormatter(String ruta, List<String> lista) throws IOException{
        Formatter ft = new Formatter(ruta);
        for(String s : lista){
            ft.format(s+"%n");
        }
        ft.close();
    }

    /**
     * Escribe datos en un archivo de texo
     * @param ruta ruta Ruta donde se va a crear el archivo
     * @param lista Información a guardar en el archivo
     * @param concat True si se concatena los nuevos datos sin sobreescibir todo el archivo
     * @throws IOException
     */
    public static void escribirArchivoBufferedWriter(String ruta, ArrayList<String> lista, boolean concat) throws IOException{

        FileWriter fw = new FileWriter(ruta, concat);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String string : lista) {
            bw.write(string);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    /**
     * Serializa un objeto en disco
     * @param ruta Ruta del archivo donde se va a serializar el objeto
     * @param objeto Objeto a serializar
     * @throws IOException
     */
    public static void serializarObjeto(String ruta, Object objeto, boolean concat) {
        try
        {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ruta, concat));
            os.writeObject(objeto);
            os.close();
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }

    /**
     * Deserializa un objeto que está guardado en disco
     * @param ruta Ruta del archivo a deserializar
     * @return Objeto deserializado
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void deserializarObjeto(Object objeto,String ruta) {
        try {
            ObjectInputStream importar = new ObjectInputStream(new FileInputStream("src/BaseDatos/serializacion/clientes.dat"));
            objeto = importar.readObject();
            importar.close();

        } catch (Exception e) {

        }
    }

    /**
     * Serializa un objeto en un archivo en formato XML
     * @param ruta Ruta del archivo donde se va a serializar el objeto
     * @param objeto Objeto a serializar
     * @throws FileNotFoundException
     */
    public static void serializarObjetoXMLConLocalDate(String ruta, Object objeto) throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(ruta));
        encoder.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
            @Override
            protected Expression instantiate(Object localDate, Encoder out) {
                return new Expression(localDate,LocalDate.class,"parse",new Object[]{localDate.toString()});
            }
        });
        encoder.setPersistenceDelegate(LocalDateTime.class, new PersistenceDelegate() {
            @Override
            protected Expression instantiate(Object localDateTime, Encoder out) {
                return new Expression(localDateTime, LocalDateTime.class,"parse",new Object[]{localDateTime.toString()});
            }
        });
        encoder.writeObject(objeto);
        encoder.close();
    }

    /**
     * Deserializa un objeto desde un archivo XML
     * @param ruta Ruta del archivo a deserializar
     * @return Objeto deserializado
     * @throws IOException
     */
    public static Object deserializarObjetoXML(String ruta) throws IOException{
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(ruta));
        Object objeto = decoder.readObject();
        decoder.close();

        return objeto;
    }


    public static void serializarObjetoXML(String ruta, Object objeto) throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(ruta));
        encoder.writeObject(objeto);
        encoder.close();
    }

    public static void serializarObjetoXML2(String ruta, Object objeto) throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(ruta));
        encoder.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
            @Override
            protected Expression instantiate(Object localDate, Encoder out) {
                return new Expression(localDate,LocalDate.class,"parse",new Object[]{localDate.toString()});
            }
        });
        encoder.setPersistenceDelegate(LocalDateTime.class, new PersistenceDelegate() {
            @Override
            protected Expression instantiate(Object localDateTime, Encoder out) {
                return new Expression(localDateTime, LocalDateTime.class,"parse",new Object[]{localDateTime.toString()});
            }
        });
        encoder.writeObject(objeto);
        encoder.close();
    }
}
