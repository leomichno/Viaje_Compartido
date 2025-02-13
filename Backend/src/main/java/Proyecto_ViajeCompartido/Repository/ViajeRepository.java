package Proyecto_ViajeCompartido.Repository;


import Proyecto_ViajeCompartido.DTO.ViajeDTO;
import Proyecto_ViajeCompartido.Entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje,Long> {
    public boolean existsByUsuarios_IdAndId(long idPasajero,long idViaje);

    List<Viaje> findByUsuarios_Id(long idUsuario);
}
