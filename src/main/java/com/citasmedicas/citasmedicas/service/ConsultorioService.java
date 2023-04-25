package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;

public interface ConsultorioService {
    //ver consultorio disponibles
    List<ConsultorioDto> getConsultorios(); //ok
    void createConsultorio(ConsultorioDto consultorioDto); //ok
    void updateConsultorio(ConsultorioDto consultorioDto); //pdt
    void deleteConsultorio(Long id); //pdt
    ConsultorioDto getConsultorioById(Long id); //pdt
    
    
    //crear consultorio
    //actualizar consultorio
    //eliminar consultorio
    //buscar consultorio x id
    
}
