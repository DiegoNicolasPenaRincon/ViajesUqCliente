package co.edu.uniquindio.agencia.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Destino implements Serializable {

    private String nombre;

    private String ciudad;

    private String descripcion;

    private ArrayList<String> rutasImagenes;

    private Clima clima;

}
