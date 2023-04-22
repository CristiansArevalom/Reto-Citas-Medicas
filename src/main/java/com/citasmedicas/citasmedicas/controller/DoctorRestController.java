package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.DoctorRequestDto;
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
    @PostMapping("api/doctores/registrar")
    public ResponseEntity <String> postDoctoresRegister(@RequestBody DoctorRequestDto doctor){
        try {
            doctorService.createDoctor(doctor);
            return ResponseEntity.ok("Doctor creado satisfactoriamente");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
        }
        
        
    }
}
