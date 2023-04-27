package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;

public interface ConsultorioAsignadoService {
    //ver consultorios asignados
    List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados();
    //crear consultorios asignados
    void createConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto);
    //actualizar consultorios asignados
    void updateConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto);
    //eliminar consultorios asignados
    void deleteConsultorioAsignado(Long id);
    //ver todos los detalles asignados de un consultorio
    ConsultorioAsignadoResponseDto getConsultorioAsignadoById(Long id);    
    //ConsultorioAsignadoResponseDto getConsultorioAsignadoByDoctor(Long id);    ver coo hacerlo
    //ConsultorioAsignadoResponseDto getConsultorioAsignadoByConsultorio(Long id); ver como hacerlo

}
