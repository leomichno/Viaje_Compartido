package Proyecto_ViajeCompartido.Config;

import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import Proyecto_ViajeCompartido.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserSecurityService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UserSecurityService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario "+username+" no encontrado"));

        System.out.println("usuario: "+usuario.getUsername());
        System.out.println("password: "+usuario.getPassword());


        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getTipoUsuario())
                .build();
    }
}
