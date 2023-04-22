package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.service.DoctorService;

@RestController
public class DoctorRestController {
    private DoctorService doctorService;

    
    public DoctorRestController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping("api/doctores")
    public List<DoctorResponseDto> getDoctores(){
        return doctorService.getDoctores();
    }
}
