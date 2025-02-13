package Proyecto_ViajeCompartido.Service;


import Proyecto_ViajeCompartido.DTO.RegisterDTO;
import Proyecto_ViajeCompartido.Entity.Usuario.Conductor;
import Proyecto_ViajeCompartido.Repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ConductorService(ConductorRepository conductorRepository, PasswordEncoder passwordEncoder){
        this.conductorRepository=conductorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Conductor crearConductor(RegisterDTO dto){
        Conductor conductor= new Conductor(dto.getNombre(),dto.getUsername(),dto.getEmail(),passwordEncoder.encode(dto.getPassword()),0,dto.getTipoUsuario(),null);
        return conductorRepository.save(conductor);
    }

    public List<Conductor> getConductores(){

        return conductorRepository.findAll();
    }

    public Conductor getConductor(String username){
        return conductorRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("CONDUCTOR no encontrado"));
    }


}
