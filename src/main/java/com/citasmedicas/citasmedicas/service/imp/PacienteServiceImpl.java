package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.PacienteRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;
import com.citasmedicas.citasmedicas.exceptions.PacienteAlreadyExistExceptions;
import com.citasmedicas.citasmedicas.exceptions.PacienteDoesntExistExceptions;
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
            paciente.getId(),
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
   
        Optional <Paciente> pacienteOpc = pacienteRepository.findByCedula(paciente.getCedula());
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
        Optional<Paciente> pacienteOp = pacienteRepository.findById(paciente.getId());
        if(pacienteOp.isEmpty()){
            throw new PacienteDoesntExistExceptions("El paciente indicado no existe");
        }
        Paciente pacienteDb = (Paciente) pacienteOp.get();
        pacienteDb.setNombre(paciente.getNombre());
        pacienteDb.setApellido(paciente.getApellido());
        pacienteDb.setCedula(paciente.getCedula());
        pacienteDb.setEdad(paciente.getEdad());
        pacienteDb.setTelefono(paciente.getTelefono());
        pacienteDb=pacienteRepository.save(pacienteDb);
    }


    @Override
    public void deletePaciente(Long id) {
        Optional <Paciente>pacienteOp =pacienteRepository.findById(id);
        if(pacienteOp.isEmpty()){
            throw new PacienteDoesntExistExceptions("El paciente no existe");
        }
        Paciente pacienteDb = (Paciente) pacienteOp.get();
        pacienteRepository.delete(pacienteDb);
    }


    @Override
    public PacienteResponseDto getPacienteById(Long Id) {
       Optional <Paciente> pacienteOp = pacienteRepository.findById(Id);
       if(pacienteOp.isEmpty()){
        throw new PacienteDoesntExistExceptions("El paciente no existe");
       }
       Paciente pacienteDb = pacienteOp.get();
        PacienteResponseDto paciente = new PacienteResponseDto(pacienteDb.getId(),pacienteDb.getNombre(), 
        pacienteDb.getApellido(), 
        pacienteDb.getCedula(), 
        pacienteDb.getEdad(),
        pacienteDb.getTelefono());
       return paciente;
    }
    
    
}
