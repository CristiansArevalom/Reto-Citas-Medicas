package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.repository.EspecialidadRepository;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

import jakarta.annotation.PostConstruct;

@Service
public class EspecialidadesServiceImpl implements EspecialidadesService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadesServiceImpl (EspecialidadRepository especialidadRepository){
        this.especialidadRepository=especialidadRepository;
    }
    @PostConstruct
    @Override
    public void fillEspecialidades() {
        // LLENANDO AUTOMATICAMENTE LA TABLA ESPECIALIDAD
        
        try {
            //Si no existe la especialidad, entonces insertela, si ya existe, no.
            List<Especialidad> especialidades = especialidadRepository.findAll();
            List<String> nombreEspecialidades=especialidades.stream().map(especialidad->especialidad.getNombre()).collect(Collectors.toList());
            for(EnumEspecialidad enumEspecialidad : EnumEspecialidad.values()){
                if(!nombreEspecialidades.contains(enumEspecialidad.getNombreEnum())){
                    Especialidad especialidad= new Especialidad(null, enumEspecialidad);
                    especialidadRepository.save(especialidad);
                }
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error al llenar especialdiades"+ex);
        }
    }
    

    @Override
    public List<EspecialidadDto> getEspecialidades() {
        //obteniendo especialidades

        try{
            List<Especialidad> especialidades = especialidadRepository.findAll();
            //llenando DTO

            return especialidades.stream().map(
                    especialidad->new EspecialidadDto(
                    especialidad.getId()
                    ,especialidad.getNombre()
                    ,especialidad.getDescripcion())
                ).collect(Collectors.toList());
        }catch(RuntimeException ex){
            throw new RuntimeException("ERROR AL LEER LAS ESPECIALIDADES");
        } 
    }

    @Override
    public EspecialidadDto getEspecialidadById(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEspecialidadById'");
    }
    @Override
    public EspecialidadDto getEspecialidadByNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEspecialidadByEnumEspecialidad'");
    }

}
