package com.citasmedicas.citasmedicas.service.imp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.exceptions.EspecialidadDoesntExistExceptions;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.repository.EspecialidadRepository;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

import jakarta.annotation.PostConstruct;

@Service
public class EspecialidadesServiceImpl implements EspecialidadesService {
    
    private final EspecialidadRepository especialidadRepository;

    public EspecialidadesServiceImpl(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @PostConstruct // se ejecuta apenas inicia el servidor, llena toda la tabla de especialdiades,
    @Override
    public void fillEspecialidades() {
        // LLENANDO AUTOMATICAMENTE LA TABLA ESPECIALIDAD
        try {
            // Si no existe la especialidad, entonces insertela, si ya existe, no.
            List<Especialidad> especialidades = especialidadRepository.findAll();
            List<String> nombreEspecialidades = especialidades.stream().map(especialidad -> especialidad.getNombre())
                    .collect(Collectors.toList());
            for (EnumEspecialidad enumEspecialidad : EnumEspecialidad.values()) {
                if (!nombreEspecialidades.contains(enumEspecialidad.getNombreEnum())) {
                    Especialidad especialidad = new Especialidad(null, enumEspecialidad);
                    especialidadRepository.save(especialidad);
                }
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error al llenar especialdiades" + ex);
        }
    }

    @Override
    public List<EspecialidadDto> getEspecialidades() {
        // obteniendo especialidades

        try {
            List<Especialidad> especialidades = especialidadRepository.findAll();
            // llenando DTO

            return especialidades.stream().map(
                    especialidad -> new EspecialidadDto(
                            especialidad.getId(), especialidad.getNombre(), especialidad.getDescripcion()))
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new RuntimeException("ERROR AL LEER LAS ESPECIALIDADES");
        }
    }

    // preguntar busquedaByNombre
    // Preguntar como hacer que las credenciales de la BD se consuman desde un JSON
    @Override
    public EspecialidadDto getEspecialidadByNombre(String nombre) {
        /*
         * trae el tipo de dato enumEspecialidad en base al string nombre; Primero busca
         * si existe en la clase, ya que se definio que es enum y debe si o si existir
         * en dica clase,
         * //TRY CATCH , si NO existe en enum especialidad arroja error,
         */
        try {
            EnumEspecialidad enumPorNombre = EnumEspecialidad.valueOf(nombre.toUpperCase());
            Optional<Especialidad> especialidad = especialidadRepository.findByNombre(enumPorNombre);
            if (especialidad.isEmpty()) {
                throw new EspecialidadDoesntExistExceptions("No existe la especialidad con excactamente ese nombre");
            }
            return new EspecialidadDto(especialidad.get().getId(), especialidad.get().getNombre(),
                    especialidad.get().getDescripcion());
        } catch (IllegalArgumentException ex) {
            throw new EspecialidadDoesntExistExceptions("No existe la especialidad con excactamente ese nombre");
        }
    }

    
    @Override
    public EspecialidadDto getEspecialidadById(Long id) {
        Optional<Especialidad> especialidad = especialidadRepository.findById(id);
        if (especialidad.isEmpty()) {
            throw new EspecialidadDoesntExistExceptions("No existe la especialidad con ese ID");
        }
        // Especialidad especialidades=especialidad.get(); Para aceder a los atributos
        // de especialdiad
        return new EspecialidadDto(especialidad.get().getId(), especialidad.get().getNombre(),
                especialidad.get().getDescripcion());
    }

    @Override
    public List<EspecialidadDto> getEspecialidadesByNombre(String nombre) {
        //TRAE LAS ESPECIALIDADES EN DONDE EL NOMBRE ESTE CONTENIDO
        //select * from especialidades where nombre like '%MEDICINA%';
        /* Could not create query for public abstract java.util.List 
        com.citasmedicas.citasmedicas.model.repository.EspecialidadRepository.findByNombreContainingIgnoreCase
        (java.lang.String); Reason: Failed to create query for method public abstract j
        ava.util.List com.citasmedicas.citasmedicas.model.repository.EspecialidadRepository.findByNombreContainingIgnoreCase(java.lang.String); 
        Unable to ignore case of com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad types,
         the property 'nombre' must reference a String */
        /* 
         try{
            /*solucion temporal, busca primero en los enum definidos si existen algunos con ese nombre, traiga en 
            una lista los posibles resultados
            
            List<EnumEspecialidad> especialidadesEnum = Arrays.asList(EnumEspecialidad.values());
            List<EnumEspecialidad> especialidadesMatchEnum =especialidadesEnum.stream()
                                                    .filter(especialidad -> especialidad.getNombreEnum().contains(nombre.toUpperCase()))
                                                    .collect(Collectors.toList());
            System.out.println(especialidadesMatchEnum.size());
            //Ahora busquelos en la base de datos..       
            
           // List<Especialidad> especialides=especialidadRepository.findByNombreContaining(enumEspecialidad);
            return especialides.stream().map(
                especialidad -> new EspecialidadDto(especialidad.getId(), especialidad.getNombre(), especialidad.getDescripcion())
            ).collect(Collectors.toList());
        }catch (RuntimeException ex){
            throw new EspecialidadDoesntExistExceptions("No existe especialidad con ese nombre "+nombre + ex);
        }
        */
        return null;

        // TODO Auto-generated method stub
    }
}