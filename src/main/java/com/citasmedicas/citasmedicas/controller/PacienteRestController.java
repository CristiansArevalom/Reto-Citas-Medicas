package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.PacienteDto;
import com.citasmedicas.citasmedicas.service.PacienteService;

@RestController
public class PacienteRestController {
    private PacienteService pacienteService;

    public PacienteRestController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("api/pacientes")
    public List<PacienteDto> getPacientes(){
        return pacienteService.getPacientes();
    }

   

    
}
