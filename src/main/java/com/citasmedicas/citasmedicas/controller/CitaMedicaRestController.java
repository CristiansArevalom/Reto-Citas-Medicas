package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaResponseDto;
import com.citasmedicas.citasmedicas.service.CitasMedicasService;

@RestController
public class CitaMedicaRestController {
    private CitasMedicasService citasMedicasService;

    public CitaMedicaRestController(CitasMedicasService citasMedicasService){
        this.citasMedicasService=citasMedicasService;
    }
    @GetMapping("api/citasMedicas/consultorioAsignado={idConsultorioAsignado}")
    public List<CitaMedicaResponseDto> getCitasMedicasByConsultorioAsignadoId(@PathVariable("idConsultorioAsignado")Long id){
        return citasMedicasService.getCitasMedicasByConsultorioAsignadoId(id);
    }
    @GetMapping("api/citasMedicas")
    public List<CitaMedicaResponseDto> getCitasMedicas(){
        return citasMedicasService.getCitasMedias();
    }
    @PostMapping("api/citasMedicas/agendar")
    public CitaMedicaResponseDto saveCitaMedica(@RequestBody CitaMedicaRequestDto citaMedicaRequestDto){

        return citasMedicasService.createCitaMedica(citaMedicaRequestDto);
    }
}
