package Proyecto_ViajeCompartido.DTO;


import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String username;
    private String password;
    private String nombre;
    private String tipoUsuario;
}
