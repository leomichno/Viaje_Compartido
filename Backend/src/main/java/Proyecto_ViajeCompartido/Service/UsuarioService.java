package Proyecto_ViajeCompartido.Service;


import Proyecto_ViajeCompartido.DTO.UsuarioDto;
import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import Proyecto_ViajeCompartido.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUser(String username){
        Usuario user = usuarioRepository.findByUsername(username).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        return user;
    }

}
