package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.PacienteRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;

public interface PacienteService {
    
    List<PacienteResponseDto> getPacientes();
    PacienteResponseDto getPacienteById(Long Id);
    PacienteResponseDto getPacienteByCedula(Integer cedula);
    void createPaciente(PacienteRequestDto paciente);
    void updatePaciente(PacienteRequestDto paciente);
    void deletePaciente(Long id);

}
