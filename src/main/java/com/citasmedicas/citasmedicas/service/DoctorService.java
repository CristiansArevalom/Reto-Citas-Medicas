package com.citasmedicas.citasmedicas.service;

import java.util.List;
import com.citasmedicas.citasmedicas.controller.dto.DoctorRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;

public interface DoctorService {
    List<DoctorResponseDto>getDoctores();
    DoctorResponseDto getDoctoresById(Long id);
    List<DoctorResponseDto> getDoctoresByEspecialidad(String especialidad);
    void createDoctor(DoctorRequestDto doctor);
    void updateDoctor(DoctorRequestDto doctor);
    void deleteDoctor(Long id);
}
