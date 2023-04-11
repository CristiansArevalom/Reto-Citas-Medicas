package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @PutMapping("api/pacientes/actualizar/{id}")
    public ResponseEntity<String> putPacientesUpdate(@PathVariable("id") Long id, @RequestBody PacienteRequestDto paciente){
        paciente.setId(id); // pra no tener que colocar el id en el body, sino solo en la url
        pacienteService.updatePaciente(paciente);
        return ResponseEntity.ok("Paciente actualizado correctamente");
    }
    
    @DeleteMapping("api/pacientes/eliminar/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable("id")Long id){
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok("paciente eliminado correctamente");

    } 

    
}
