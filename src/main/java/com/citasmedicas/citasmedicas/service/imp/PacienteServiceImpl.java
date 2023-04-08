package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.PacienteRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;
import com.citasmedicas.citasmedicas.exceptions.PacienteAlreadyExistExceptions;
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
    public List<PacienteResponseDto> getPacientes() {
        try{
        List<Paciente> pacientes = pacienteRepository.findAll();

        //llenando el DTO de paciente
        return pacientes.stream().map(paciente -> new PacienteResponseDto(
            paciente.getNombre(), 
            paciente.getApellido(),
             paciente.getCedula(), 
             paciente.getEdad(), 
             paciente.getTelefono())).collect(Collectors.toList());
        }catch(Exception ex){
            throw new UnsupportedOperationException("Unimplemented method 'getPacientes'");

        }          
    }


    @Override
    public void createPaciente(PacienteRequestDto paciente) {
   
        Optional pacienteOpc = pacienteRepository.findByCedula(paciente.getCedula());
        if(pacienteOpc.isPresent()){
            throw new PacienteAlreadyExistExceptions("Paciente ya registrado");
        }
        Paciente pacienteDb = new Paciente();
        pacienteDb.setNombre(paciente.getNombre());
        pacienteDb.setApellido(paciente.getApellido());
        pacienteDb.setCedula(paciente.getCedula());
        pacienteDb.setEdad(paciente.getEdad());
        pacienteDb.setTelefono(paciente.getTelefono());
        pacienteDb=pacienteRepository.save(pacienteDb);//usa el save que trae JPA


    }


    @Override
    public void updatePaciente(PacienteRequestDto paciente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePaciente'");
    }


    @Override
    public void deletePaciente(PacienteRequestDto paciente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePaciente'");
    }
    
}
