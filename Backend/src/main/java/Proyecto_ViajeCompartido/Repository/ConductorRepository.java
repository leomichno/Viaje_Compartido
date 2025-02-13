package Proyecto_ViajeCompartido.Repository;

import Proyecto_ViajeCompartido.Entity.Usuario.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ConductorRepository extends JpaRepository<Conductor,Long> {

    Optional<Conductor> findByUsername(String username);
}
