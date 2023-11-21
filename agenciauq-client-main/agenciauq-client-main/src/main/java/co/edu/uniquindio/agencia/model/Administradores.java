package co.edu.uniquindio.agencia.model;

import co.edu.uniquindio.agencia.exceptions.*;
import lombok.*;
import utils.Persistencia_Serializacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Administradores implements Serializable {

    private String nombre;
    private String cedula;
    private String contrasena;

}
