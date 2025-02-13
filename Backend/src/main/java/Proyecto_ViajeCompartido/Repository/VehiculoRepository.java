package Proyecto_ViajeCompartido.Repository;

import Proyecto_ViajeCompartido.Entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
}
