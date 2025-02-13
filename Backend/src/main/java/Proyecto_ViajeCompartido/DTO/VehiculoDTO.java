package Proyecto_ViajeCompartido.DTO;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class VehiculoDTO {
    private String descripcion;

    private Integer capacidadDePasajeros;

    private LocalDate anoDeFabricacion;

    private double valorDeMercado;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCapacidadDePasajeros() {
        return capacidadDePasajeros;
    }

    public void setCapacidadDePasajeros(Integer capacidadDePasajeros) {
        this.capacidadDePasajeros = capacidadDePasajeros;
    }

    public LocalDate getAnoDeFabricacion() {
        return anoDeFabricacion;
    }

    public void setAnoDeFabricacion(LocalDate anoDeFabricacion) {
        this.anoDeFabricacion = anoDeFabricacion;
    }

    public double getValorDeMercado() {
        return valorDeMercado;
    }

    public void setValorDeMercado(double valorDeMercado) {
        this.valorDeMercado = valorDeMercado;
    }
}
