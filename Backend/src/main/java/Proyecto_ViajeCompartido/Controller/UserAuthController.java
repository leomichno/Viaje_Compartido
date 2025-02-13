package Proyecto_ViajeCompartido.Controller;


import Proyecto_ViajeCompartido.DTO.LoginDTO;
import Proyecto_ViajeCompartido.DTO.RegisterDTO;
import Proyecto_ViajeCompartido.JWT.JwtUtil;
import Proyecto_ViajeCompartido.Service.ConductorService;
import Proyecto_ViajeCompartido.Service.PasajeroService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "Authorization")
public class UserAuthController {


    private final ConductorService conductorService;
    private final PasajeroService pasajeroService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserAuthController(ConductorService conductorService, PasajeroService pasajeroService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.conductorService = conductorService;
        this.pasajeroService = pasajeroService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());
        Authentication authentication= this.authenticationManager.authenticate(login);
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", ""))
                .collect(Collectors.joining(","));

        
        String jwt = this.jwtUtil.create(loginDTO.getUsername(),roles);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(registerDTO.getTipoUsuario().equals("CONDUCTOR")){
            conductorService.crearConductor(registerDTO);
        }else{
            pasajeroService.crearPasajero(registerDTO);
        }
        return ResponseEntity.ok("Usuario registrado con éxito");
    }


}
