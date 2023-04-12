package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.repository.EspecialidadRepository;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

@Service
public class EspecialidadesServiceImpl implements EspecialidadesService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadesServiceImpl (EspecialidadRepository especialidadRepository){
        this.especialidadRepository=especialidadRepository;
    }
    @Override
    public List<EspecialidadDto> getEspecialidades() {
        //obteniendo especialidades

        try{
            List<Especialidad> especialidades = especialidadRepository.findAll();
            /*lenando especialidad DTO de manera vieja de prog funcional
            especialidades.stream().map(
                especialidad ->new EspecialidadDto(especialidad.getId(), null, null))
            */
            


        }catch(RuntimeException ex){
            throw new RuntimeException();
        }
*/
        return null;
    }

    @Override
    public EspecialidadDto getEspecialidadByNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEspecialidadByNombre'");
    }

    @Override
    public EspecialidadDto getEspecialidadById(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEspecialidadById'");
    }
    
}
