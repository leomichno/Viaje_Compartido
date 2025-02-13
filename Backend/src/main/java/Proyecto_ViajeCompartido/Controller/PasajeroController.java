package Proyecto_ViajeCompartido.Controller;


import Proyecto_ViajeCompartido.Service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pasajero")
public class PasajeroController {
    @Autowired
    private PasajeroService pasajeroService;

    @PostMapping("/unirse")
    public ResponseEntity<Map<String, Object>> unirseAViaje(@RequestBody Long idViaje, @AuthenticationPrincipal User user) {
        return pasajeroService.unirseAViaje(idViaje, user.getUsername());
    }
}
