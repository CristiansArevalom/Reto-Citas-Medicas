package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;

public interface EspecialidadesService {
    void fillEspecialidades();
    List<EspecialidadDto> getEspecialidades();
    EspecialidadDto getEspecialidadByNombre(String nombre);
    List<EspecialidadDto>getEspecialidadesByNombre(String nombre);//va a funcionar como un linke trae las especialidades que mas coincidan,se suspende
    EspecialidadDto getEspecialidadById(Long id);

}
