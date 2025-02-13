package Proyecto_ViajeCompartido.Controller;


import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import Proyecto_ViajeCompartido.Service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public Usuario getUser(@AuthenticationPrincipal User user){
        return usuarioService.getUser(user.getUsername());
    }




}
