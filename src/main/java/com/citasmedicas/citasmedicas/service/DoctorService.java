package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.DoctorRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;

public interface DoctorService {
    //ver doctores disponibles
    //crear doctor
    //actualizar doctor
    //eliminar doctor
    //buscar doctor x id
    //buscar doctores con especialidad especifica
    List<DoctorResponseDto>getDoctores();
    void createDoctor(DoctorRequestDto doctor);
    void updateDoctor(DoctorRequestDto doctor);
    void deleteDoctor(Long id);
    DoctorResponseDto getDoctoresById(Long id);
    DoctorResponseDto getDoctoresByEspecialidad(EspecialidadDto especialidad); //ver como hacerlo



}
