package com.citasmedicas.citasmedicas.service;

import java.time.LocalDateTime;
import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;

public interface ConsultorioAsignadoService {
    List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados();
    ConsultorioAsignadoResponseDto getConsultorioAsignadoById(Long id);    
    List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByConsultorio(Long id);
    List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByEspecialidad(String especialidad);
    List<ConsultorioAsignadoResponseDto> findAllByBetweenAsignedDates(LocalDateTime fechaInicio,LocalDateTime fechaFin);
    void createConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto);
    void updateConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto);
    void deleteConsultorioAsignado(Long id);
  //  List<ConsultorioAsignadoResponseDto> getConsultoriosAsignadosByEspecialidadAndAvailableByCitaDates(String especialidad, LocalDateTime fechaCitaInicio,LocalDateTime fechaCitaFin);
}
