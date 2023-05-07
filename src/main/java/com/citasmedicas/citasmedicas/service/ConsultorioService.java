package com.citasmedicas.citasmedicas.service;

import java.util.List;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;

public interface ConsultorioService {
    List<ConsultorioDto> getConsultorios();
    ConsultorioDto getConsultorioById(Long id);
    void createConsultorio(ConsultorioDto consultorioDto);
    void updateConsultorio(ConsultorioDto consultorioDto);
    void deleteConsultorio(Long id);
}
