package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioAlreadyExistException;
import com.citasmedicas.citasmedicas.model.entity.Consultorio;
import com.citasmedicas.citasmedicas.model.repository.ConsultorioRepository;
import com.citasmedicas.citasmedicas.service.ConsultorioService;

@Service
public class ConsultorioServiceImpl implements ConsultorioService{

    private final ConsultorioRepository consultorioRepository;
    
    public ConsultorioServiceImpl(ConsultorioRepository consultorioRepository) {
        this.consultorioRepository = consultorioRepository;
    }

    @Override
    public List<ConsultorioDto> getConsultorios() {
        try {
            List<Consultorio> consultorios = consultorioRepository.findAll();
            return consultorios.stream().map(consultorio->new ConsultorioDto(consultorio.getId(), consultorio.getCiudad(), consultorio.getDireccion(), consultorio.getNumero(), consultorio.getDescripcion())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getConsultorios'");
        }
        
    }

    @Override
    public void createConsultorio(ConsultorioDto consultorioDto) {
        //validar si ya existe el consultorio 1 busca por direccion
        //luego compara si ambos datos en todos sus registros son iguales.
        //toca sobreescribir el comparator de la clase Consultorio
        List<Consultorio> consultorioByDireccion = consultorioRepository.findByDireccion(consultorioDto.getDireccion());
        Consultorio consultorioDb = new Consultorio();
        consultorioDb.setCiudad(consultorioDto.getCiudad());
        consultorioDb.setDireccion(consultorioDto.getDireccion());
        consultorioDb.setNumero(consultorioDto.getNumero());
        consultorioDb.setDescripcion(consultorioDto.getDescripcion());
        if(consultorioByDireccion.size()>0){
            for (Consultorio consultorio : consultorioByDireccion) {
                if(consultorio.equals(consultorioDb)){
                    throw new ConsultorioAlreadyExistException("Error el consultorio a ingresar ya existe");
                }
                
            }
        }else{
            consultorioDb=consultorioRepository.save(consultorioDb);
        }
    }

    @Override
    public void updateConsultorio(ConsultorioDto consultorioDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateConsultorio'");
    }

    @Override
    public void deleteConsultorio(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteConsultorio'");
    }

    @Override
    public ConsultorioDto getConsultorioById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConsultorioById'");
    }

    
}
