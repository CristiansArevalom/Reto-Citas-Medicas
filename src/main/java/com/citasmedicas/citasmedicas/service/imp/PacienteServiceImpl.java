package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.PacienteDto;
import com.citasmedicas.citasmedicas.model.entity.Paciente;
import com.citasmedicas.citasmedicas.model.repository.PacienteRepository;
import com.citasmedicas.citasmedicas.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;


    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public List<PacienteDto> getPacientes() {
        try{
        List<Paciente> pacientes = pacienteRepository.findAll();

        //llenando el DTO de paciente
        return pacientes.stream().map(paciente -> new PacienteDto(
            paciente.getNombre(), 
            paciente.getApellido(),
             paciente.getCedula(), 
             paciente.getEdad(), 
             paciente.getTelefono())).collect(Collectors.toList());
        }catch(Exception ex){
            throw new UnsupportedOperationException("Unimplemented method 'getPacientes'");

        }   
        
    }
    
}
