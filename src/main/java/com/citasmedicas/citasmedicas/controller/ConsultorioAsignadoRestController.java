package com.citasmedicas.citasmedicas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;
import com.citasmedicas.citasmedicas.service.ConsultorioAsignadoService;

@RestController
public class ConsultorioAsignadoRestController {
    private ConsultorioAsignadoService consultorioAsignadoService;

    public ConsultorioAsignadoRestController(ConsultorioAsignadoService consultorioAsignadoService) {
        this.consultorioAsignadoService = consultorioAsignadoService;
    }

    @GetMapping("api/ConsultoriosAsignados")
    public List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados(){
        return consultorioAsignadoService.getConsultoriosAsignados();
    }
    @GetMapping("api/ConsultoriosAsignados/{id_consultorio}")
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByConsultorio(@PathVariable Long id_consultorio){
        return consultorioAsignadoService.getConsultorioAsignadoByConsultorio(id_consultorio);
    }
    @GetMapping("api/ConsultoriosAsingados/{especialidad}")
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByEspecialidad(@PathVariable String especialidad){
        return consultorioAsignadoService.getConsultorioAsignadoByEspecialidad(especialidad);
    }
    /*
    @GetMapping("/api/ConsultoriosAsingados/{especialidad}/{fechaInicio}/{fechaFin}")
    public List<ConsultorioAsignadoResponseDto> getConsultoriosAsignadosByEspecialidadAndAvailableByCitaDates(
        @PathVariable String especialidad, 
        @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime fechaInicio,
        @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime fechaFin) {
        return consultorioAsignadoService.getConsultoriosAsignadosByEspecialidadAndAvailableByCitaDates(especialidad, fechaInicio, fechaFin);
    }
 */
    @PostMapping("api/ConsultoriosAsignados/registrar")
    public ResponseEntity<String> postConsultorioAsignadoRegistrar(@RequestBody ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto){
        try{
            consultorioAsignadoService.createConsultorioAsignado(consultorioAsignadoRequestDto);
        return ResponseEntity.ok("Se asigno consultoorio satisfactoriamente");
        
    }catch(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
    }
}
}
