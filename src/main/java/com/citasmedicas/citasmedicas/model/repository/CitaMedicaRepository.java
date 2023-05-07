package com.citasmedicas.citasmedicas.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citasmedicas.citasmedicas.model.entity.CitaMedica;

public interface CitaMedicaRepository extends JpaRepository <CitaMedica,Long>{
    List<CitaMedica> findAll();
    Optional<CitaMedica> findById(Long id);
    List<CitaMedica> findAllByDoctorId(Long id);
    List<CitaMedica> findAllByPacienteId(Long id);
    List<CitaMedica> findAllByConsultorioAsignadoId(Long id);
}
