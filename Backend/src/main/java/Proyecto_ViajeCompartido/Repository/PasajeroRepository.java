package Proyecto_ViajeCompartido.Repository;

import Proyecto_ViajeCompartido.Entity.Usuario.Pasajero;
import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasajeroRepository extends JpaRepository<Pasajero,Long> {

    Optional<Pasajero> findByUsername(String username);
}
