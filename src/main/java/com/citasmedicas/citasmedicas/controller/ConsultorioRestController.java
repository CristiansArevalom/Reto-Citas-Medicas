package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;
import com.citasmedicas.citasmedicas.service.ConsultorioService;

@RestController
public class ConsultorioRestController {
    private ConsultorioService consultorioService;

    public ConsultorioRestController(ConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }
    
    @GetMapping("api/Consultorios")
    public List<ConsultorioDto> getConsultorios(){
        return consultorioService.getConsultorios();
    }
    @PostMapping("api/consultorios/registrar")
    public ResponseEntity <String> postConsultoriosRegistrar(@RequestBody ConsultorioDto consultorioDto){
        try{
            consultorioService.createConsultorio(consultorioDto);
            return ResponseEntity.ok("Consultorio creado satisfactoriamente");
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
        }
    }
    
}
