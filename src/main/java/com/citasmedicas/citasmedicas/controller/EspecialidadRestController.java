package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

@RestController
public class EspecialidadRestController {
    private EspecialidadesService especialidadesService;

    public EspecialidadRestController(EspecialidadesService especialidadesService){
        this.especialidadesService=especialidadesService;
    }
    @GetMapping("api/especialidades")
    public List<EspecialidadDto> getEspecialidades(){
        return especialidadesService.getEspecialidades();
    }
    

}
