package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    
}
