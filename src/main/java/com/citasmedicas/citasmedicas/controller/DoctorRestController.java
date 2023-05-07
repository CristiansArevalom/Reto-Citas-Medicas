package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("api/doctores/{id}")
    public DoctorResponseDto getDoctor(@PathVariable("id") Long id){
        return doctorService.getDoctoresById(id);
    }
    @PutMapping("api/doctores/actualizar/{id}")
    public ResponseEntity<String> putDoctorUpdate(@PathVariable("id") Long id, @RequestBody DoctorRequestDto doctorRequestDto){
        doctorRequestDto.setId(id);
        doctorService.updateDoctor(doctorRequestDto);
        return ResponseEntity.ok("Doctor actualizado satisfactoriamente");
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

    @DeleteMapping("api/doctores/eliminar/{id}")
    public ResponseEntity <String> deleteDoctor(@PathVariable("id") Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor eliminado correctamente");
    }
    @GetMapping("api/doctores/especialidad/{especialidad}")
    public List<DoctorResponseDto> getDoctoresByEspecialidad(@PathVariable("especialidad") String especialidad){
        return doctorService.getDoctoresByEspecialidad(especialidad);
    }
}
