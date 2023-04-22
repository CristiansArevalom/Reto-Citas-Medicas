package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.DoctorRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.repository.DoctorRepository;
import com.citasmedicas.citasmedicas.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override//trae los doctores con su especialidad
    public List<DoctorResponseDto> getDoctores() {
        try {
            List <Doctor> doctores= doctorRepository.findAllWithEspecialidad();
            //select  * from doctores d inner join especialidades es on d.id_especialidad = es.id;            List<Doctor> doctores = doctorRepository.findAll();
            //llenando DTO doctores
            return doctores.stream().map(doctor ->
                new DoctorResponseDto(doctor.getId(), 
                doctor.getNombre(), 
                doctor.getApellido(), 
                doctor.getCorreo(), 
                doctor.getEspecialidad().getNombre())
                ).collect(Collectors.toList());
            
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("ERROR al buscar" + e.getStackTrace());
        }
    }

    @Override
    public void createDoctor(DoctorRequestDto doctor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDoctor'");
    }

    @Override
    public void updateDoctor(DoctorRequestDto doctor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDoctor'");
    }

    @Override
    public void deleteDoctor(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDoctor'");
    }

    @Override
    public DoctorResponseDto getDoctoresById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDoctoresById'");
    }

    @Override
    public DoctorResponseDto getDoctoresByEspecialidad(EspecialidadDto especialidad) {
        // TODO Auto-generated method stub
        /*select doctores from doctores inner join especialidad where especialidad.nombre like %especialidad% */
        throw new UnsupportedOperationException("Unimplemented method 'getDoctoresByEspecialidad'");
    }

    
}
