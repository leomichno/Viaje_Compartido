package Proyecto_ViajeCompartido.Entity;

import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class    Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany()
    @JoinTable(
            name = "viaje_usuarios",
            joinColumns = @JoinColumn(name = "viaje_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();


    private String origen;


    private String destino;


    private double costoTotal;


    private LocalDate fechaDeViaje;

    private Integer capacidadDisponible;

    public Viaje(){}

    public Viaje(String origen,String destino,double costoTotal,LocalDate fechaDeViaje){
        this.origen=origen;
        this.destino=destino;
        this.costoTotal=costoTotal;
        this.fechaDeViaje=fechaDeViaje;
        capacidadDisponible=0;
    }


    public void agregarPersona(Usuario usuario){
        usuarios.add(usuario);
        capacidadDisponible--;
    }


}
