package Proyecto_ViajeCompartido.Repository;

import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
}
