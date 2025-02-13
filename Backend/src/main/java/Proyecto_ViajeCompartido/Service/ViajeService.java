package Proyecto_ViajeCompartido.Service;


import Proyecto_ViajeCompartido.DTO.ViajeDTO;
import Proyecto_ViajeCompartido.Entity.Usuario.Conductor;
import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import Proyecto_ViajeCompartido.Entity.Viaje;
import Proyecto_ViajeCompartido.Repository.UsuarioRepository;
import Proyecto_ViajeCompartido.Repository.ViajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    private final ViajeRepository viajeRepository;
    private final ConductorService conductorService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ViajeService(ViajeRepository viajeRepository, ConductorService conductorService, UsuarioRepository usuarioRepository){
        this.viajeRepository=viajeRepository;
        this.conductorService=conductorService;
        this.usuarioRepository = usuarioRepository;
    }

    public Viaje buscarViaje(Long id){
        return viajeRepository.findById(id).orElseThrow(()->new RuntimeException("Viaje no encontrado"));
    }

    public List<Viaje> allTripsUser(String username){
        Usuario user = usuarioRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println(username);
        return viajeRepository.findByUsuarios_Id(user.getId());

    }


    public Viaje crearViaje(ViajeDTO dto,String username){
        Conductor usuario=conductorService.getConductor(username);
        Viaje viaje = new Viaje(dto.getOrigen(), dto.getDestino(), dto.getCostoTotal(), dto.getFechaDeViaje());
        if((usuario.getTipoUsuario().equals("CONDUCTOR"))&&(usuario.tieneViajeEnFecha(viaje.getFechaDeViaje()))){
            usuario.crearViaje(viaje);
            viaje.setCapacidadDisponible(usuario.getVehiculo().getCapacidadDePasajeros());
            viaje.agregarPersona(usuario);
            return viajeRepository.save(viaje);
        }

        throw new RuntimeException("El usuario deber ser conductor para crear un viaje");
    }

    @Transactional
    public List<ViajeDTO> allTrips() {
        return viajeRepository.findAll()
                .stream()
                .map(this::convertToDTO) // Convierte la entidad a DTO
                .collect(Collectors.toList());
    }

    private ViajeDTO convertToDTO(Viaje viaje) {
        return new ViajeDTO(
                viaje.getId(),
                viaje.getOrigen(),
                viaje.getDestino(),
                viaje.getFechaDeViaje(),
                viaje.getCostoTotal()
        );
    }


}
