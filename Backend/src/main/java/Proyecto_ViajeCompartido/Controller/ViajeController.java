package Proyecto_ViajeCompartido.Controller;


import Proyecto_ViajeCompartido.DTO.ViajeDTO;
import Proyecto_ViajeCompartido.Entity.Viaje;
import Proyecto_ViajeCompartido.Service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;


    @GetMapping("/trips")
    public List<ViajeDTO> allTrips(){
        return viajeService.allTrips();
    }


    @GetMapping("/trips/user")
    public List<Viaje> allTripsUser(@AuthenticationPrincipal User user){
        return viajeService.allTripsUser(user.getUsername());
    }



    @PostMapping()
    public Viaje crearViaje(@RequestBody ViajeDTO dto ,@AuthenticationPrincipal User user){
        return viajeService.crearViaje(dto, user.getUsername());
    }
}
