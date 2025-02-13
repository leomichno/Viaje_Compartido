package Proyecto_ViajeCompartido.DTO;

import Proyecto_ViajeCompartido.Entity.Vehiculo;

public class ConductorDTO {
    private String nombre;
    private String username;
    private String email;
    private String password;
    private double saldo;
    private Vehiculo vehiculo;
    private String tipoUsuario;


    public String getNombre(){
        return nombre;
    }

    public String getEmail(){return email;}

    public String getPassword(){return password;}

    public double getSaldo(){
        return saldo;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }

    public String getTipoUsuario(){
        return tipoUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
