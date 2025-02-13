package Proyecto_ViajeCompartido.Entity.Usuario;

import Proyecto_ViajeCompartido.Entity.Vehiculo;
import Proyecto_ViajeCompartido.Entity.Viaje;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.PersistenceConstructor;

@Entity
@DiscriminatorValue("PASAJERO")
public class Pasajero extends Usuario{

    public Pasajero(){};


    @PersistenceConstructor
    public Pasajero(String nombre,String username,String email,String password, double saldo,String tipoUsuario){
        super(nombre,username,email,password,saldo,tipoUsuario);
    }


    public void unirmeAViaje(Viaje viaje){
        this.agregarViaje(viaje);
    }

}
