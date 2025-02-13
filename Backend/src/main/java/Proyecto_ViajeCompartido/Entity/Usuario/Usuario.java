package Proyecto_ViajeCompartido.Entity.Usuario;


import Proyecto_ViajeCompartido.Entity.Viaje;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)

@Data
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    private String email;

    private String username;

    private String password;

    private String nombre;

    private double saldo;

    @ManyToMany(mappedBy = "usuarios")
    private List<Viaje> viajes = new ArrayList<>();

    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private String tipoUsuario;


    public Usuario() {
    }

    public Usuario(String nombre,String username,String email,String password,double saldo,String tipoUsuario){
        this.nombre=nombre;
        this.username=username;
        this.email=email;
        this.password=password;
        this.saldo=saldo;
        this.tipoUsuario=tipoUsuario;
    }

    protected void agregarViaje(Viaje viaje){
        viajes.add(viaje);
    }

    protected List<Viaje> getViajes(){
        return viajes;
    }



}
