package Proyecto_ViajeCompartido.Service;

import Proyecto_ViajeCompartido.DTO.VehiculoDTO;
import Proyecto_ViajeCompartido.Entity.Usuario.Conductor;
import Proyecto_ViajeCompartido.Entity.Vehiculo;
import Proyecto_ViajeCompartido.Repository.ConductorRepository;
import Proyecto_ViajeCompartido.Repository.UsuarioRepository;
import Proyecto_ViajeCompartido.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ConductorRepository conductorRepository;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository,ConductorRepository conductorRepository){
        this.vehiculoRepository = vehiculoRepository;
        this.conductorRepository = conductorRepository;
    }


    public String crearVehiculo(VehiculoDTO dto, String username){
        Conductor user = conductorRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario "+username+" no encontrado"));
        if(user.getVehiculo()==null){
            Vehiculo vehiculo = new Vehiculo(dto.getDescripcion(),dto.getCapacidadDePasajeros(),dto.getAnoDeFabricacion(),dto.getValorDeMercado());

            vehiculo = vehiculoRepository.save(vehiculo);

            // Asignamos el vehículo al conductor
            user.agregarVehiculo(vehiculo);
            conductorRepository.save(user);
            return "Se creo con exito";
        }else{
            return "Ya tiene un auto asignado";
        }
    }

    public List<Vehiculo> getAll(){
        return vehiculoRepository.findAll();
    }
}
