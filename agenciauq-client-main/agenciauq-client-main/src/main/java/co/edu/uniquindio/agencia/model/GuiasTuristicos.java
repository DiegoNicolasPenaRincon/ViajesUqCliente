package co.edu.uniquindio.agencia.model;

import lombok.*;
import utils.Persistencia_Serializacion;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuiasTuristicos implements Serializable {

    private String nombre;
    private String identificacion;
    private ArrayList<String> lenguajes;
    public static ArrayList<String> lenguajesDisponibles;
    private String experiencia;

}
