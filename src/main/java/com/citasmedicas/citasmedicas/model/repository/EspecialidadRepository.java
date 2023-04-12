package com.citasmedicas.citasmedicas.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.citasmedicas.citasmedicas.model.entity.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad,Long>{
        //solo podra buscar especialidades
    List<Especialidad> findAll();
    Optional <Especialidad> findByNombre(String nombre);
    Optional<Especialidad> findById(Long id);

}
