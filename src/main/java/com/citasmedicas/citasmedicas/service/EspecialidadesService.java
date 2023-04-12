package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;

public interface EspecialidadesService {
    List<EspecialidadDto> getEspecialidades();
    EspecialidadDto getEspecialidadByNombre(String nombre);
    EspecialidadDto getEspecialidadById(String nombre);

}
