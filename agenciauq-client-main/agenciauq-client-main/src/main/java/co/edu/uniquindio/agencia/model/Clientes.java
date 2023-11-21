package co.edu.uniquindio.agencia.model;

import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Clientes implements Serializable {

    private String nombre;
    private String cedula;
    private String contrasena;
    private String telefono;
    private String correo;
    private String direccion;

}
