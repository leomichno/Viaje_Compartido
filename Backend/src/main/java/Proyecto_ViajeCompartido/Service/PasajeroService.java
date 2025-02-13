package Proyecto_ViajeCompartido.Service;


import Proyecto_ViajeCompartido.DTO.PasajeroDTO;
import Proyecto_ViajeCompartido.DTO.RegisterDTO;
import Proyecto_ViajeCompartido.DTO.UnirseViajeDTO;
import Proyecto_ViajeCompartido.Entity.Usuario.Pasajero;
import Proyecto_ViajeCompartido.Entity.Viaje;
import Proyecto_ViajeCompartido.Repository.PasajeroRepository;
import Proyecto_ViajeCompartido.Repository.ViajeRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PasajeroService {


    private final PasswordEncoder passwordEncoder;
    private final PasajeroRepository pasajeroRepository;
    private final ViajeService viajeService;
    private final ViajeRepository viajeRepository;

    @Autowired
    public PasajeroService(PasswordEncoder passwordEncoder, PasajeroRepository pasajeroRepository, ViajeService viajeService, ViajeRepository viajeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.pasajeroRepository = pasajeroRepository;
        this.viajeService = viajeService;
        this.viajeRepository = viajeRepository;
    }

    public Pasajero crearPasajero(RegisterDTO dto) {
        Pasajero pasajero = new Pasajero(dto.getNombre(), dto.getUsername(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()), 0, dto.getTipoUsuario());
        return pasajeroRepository.save(pasajero);
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> unirseAViaje(Long idViaje, String username) {
        Map<String, Object> response = new HashMap<>();
        Viaje viaje = viajeService.buscarViaje(idViaje);
        Pasajero pasajero = pasajeroRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));

        if (!viajeRepository.existsByUsuarios_IdAndId(pasajero.getId(), viaje.getId())) {
            if (viaje.getCapacidadDisponible() > 0) {
                pasajero.unirmeAViaje(viaje);
                viaje.agregarPersona(pasajero);
                response.put("success", true);
                response.put("message", "SE UNIO CON EXITO A ESTE VIAJE");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "SE ALCANZO LA CAPACIDAD MAXIMA DEL VIAJE");
                return ResponseEntity.status(400).body(response); // Código 400 para error de solicitud
            }
        }

        response.put("success", false);
        response.put("message", "ERROR!! YA ESTA UNIDO A ESTE VIAJE");
        return ResponseEntity.status(400).body(response); // Código 400 para error de solicitud
    }


}
