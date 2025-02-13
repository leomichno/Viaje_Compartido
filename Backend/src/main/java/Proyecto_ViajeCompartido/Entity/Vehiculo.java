package Proyecto_ViajeCompartido.Entity;

import jakarta.persistence.*;


import java.time.LocalDate;
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Column(name = "capacidad_de_pasajeros")
    private Integer capacidadDePasajeros;

    @Column(name = "ano_de_fabricacion")
    private LocalDate anoDeFabricacion;

    @Column(name = "valor_de_mercado")
    private double valorDeMercado;

    public Vehiculo(){};


    public Vehiculo(String descripcion,Integer capacidadDePasajeros,LocalDate anoDeFabricacion,double valorDeMercado){
        this.descripcion=descripcion;
        this.capacidadDePasajeros=capacidadDePasajeros;
        this.anoDeFabricacion=anoDeFabricacion;
        this.valorDeMercado=valorDeMercado;
    }


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
