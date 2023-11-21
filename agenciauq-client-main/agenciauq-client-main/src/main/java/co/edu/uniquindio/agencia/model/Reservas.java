package co.edu.uniquindio.agencia.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservas implements Serializable {
    private Clientes cliente;
    private LocalDate fechaSolicitud;
    private LocalDate fechaViaje;
    private int numPersonas;
    private PaqueteTuristico paquete;
    private EstadoReserva estado;
    private GuiasTuristicos guiaTuristico;
    private double precioTotal;
}
