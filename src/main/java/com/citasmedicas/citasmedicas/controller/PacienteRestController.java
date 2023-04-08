package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.PacienteRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;
import com.citasmedicas.citasmedicas.service.PacienteService;

@RestController
public class PacienteRestController {
    private PacienteService pacienteService;

    public PacienteRestController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("api/pacientes")
    public List<PacienteResponseDto> getPacientes(){
        return pacienteService.getPacientes();
    }
    @PostMapping("api/pacientes/registrar")
    public ResponseEntity<String> postPacientesRegister(@RequestBody PacienteRequestDto paciente){
            pacienteService.createPaciente(paciente);
            return ResponseEntity.ok("paciente registrado correctamente");
    }

    
}
