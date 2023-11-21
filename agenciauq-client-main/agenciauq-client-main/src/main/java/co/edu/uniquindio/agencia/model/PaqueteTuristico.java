package co.edu.uniquindio.agencia.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder

public class PaqueteTuristico implements Serializable {

    private String nombre;
    private int duracion;
    private String servicios;
    private double precio;
    private int cupoMaximo;

    //fechaInicio y fechaFin son las variables que establecen la disponibilidad del paquete
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private ArrayList<Destino> destinos;
}
