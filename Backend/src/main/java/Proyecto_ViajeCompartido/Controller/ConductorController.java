package Proyecto_ViajeCompartido.Controller;


import Proyecto_ViajeCompartido.DTO.ConductorDTO;
import Proyecto_ViajeCompartido.Entity.Usuario.Conductor;
import Proyecto_ViajeCompartido.Entity.Usuario.Usuario;
import Proyecto_ViajeCompartido.Repository.ConductorRepository;
import Proyecto_ViajeCompartido.Service.ConductorService;
import Proyecto_ViajeCompartido.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductor")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;
    private UsuarioService usuarioService;


    @GetMapping()
    public List<Conductor> getConductores(){
        return conductorService.getConductores();
    }

}
