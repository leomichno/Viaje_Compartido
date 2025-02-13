package Proyecto_ViajeCompartido.Controller;

import Proyecto_ViajeCompartido.DTO.VehiculoDTO;
import Proyecto_ViajeCompartido.Entity.Vehiculo;
import Proyecto_ViajeCompartido.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/car")
public class VehiculoController {


    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }


    @PostMapping("/add")
    public String crearVehiculo(@RequestBody VehiculoDTO vehiculo, @AuthenticationPrincipal User user){
        return vehiculoService.crearVehiculo(vehiculo,user.getUsername());
    }


    @GetMapping()
    public List<Vehiculo> getAll(){
        return vehiculoService.getAll();
    }

}
