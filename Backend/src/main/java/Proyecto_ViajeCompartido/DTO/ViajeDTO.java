package Proyecto_ViajeCompartido.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ViajeDTO {
    private long id;
    private String origen;
    private String destino;
    private double costoTotal;
    private LocalDate fechaDeViaje;


    public ViajeDTO(long id, String origen, String destino, LocalDate fechaDeViaje, double costoTotal) {
        this.id=id;
        this.origen=origen;
        this.destino=destino;
        this.fechaDeViaje=fechaDeViaje;
        this.costoTotal=costoTotal;
    }
}
