package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;
import com.citasmedicas.citasmedicas.model.entity.ConsultorioAsignado;
import com.citasmedicas.citasmedicas.model.repository.ConsultorioAsignadoRepository;
import com.citasmedicas.citasmedicas.service.ConsultorioAsignadoService;
@Service
public class ConsultorioAsignadoServiceImpl implements ConsultorioAsignadoService {

    private final ConsultorioAsignadoRepository consultorioAsignadoRepository;
    


    public ConsultorioAsignadoServiceImpl(ConsultorioAsignadoRepository consultorioAsignadoRepository) {
        this.consultorioAsignadoRepository = consultorioAsignadoRepository;
    }

    @Override
    public List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados() {
        try{
            List<ConsultorioAsignado> consultorioAsignados = consultorioAsignadoRepository.findAll();
            return consultorioAsignados.stream().map(
                    consAsign -> new ConsultorioAsignadoResponseDto(consAsign.getDoctor().getId(),
                            consAsign.getDoctor().getNombre(), consAsign.getDoctor().getApellido(),
                            consAsign.getDoctor().getCorreo(), consAsign.getDoctor().getEspecialidad().getNombre(),
                            consAsign.getConsultorio().getId(), consAsign.getConsultorio().getCiudad(), consAsign.getConsultorio().getDireccion(), consAsign.getConsultorio().getNumero(),
                            consAsign.getConsultorio().getDescripcion(), consAsign.getId(), consAsign.getInicioReserva(), consAsign.getFinReserva()))
                    .collect(Collectors.toList());
        }catch(RuntimeException ex){
            throw new UnsupportedOperationException("Unimplemented method 'getConsultoriosAsignados'" + ex);
        }
    }

    @Override
    public void createConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createConsultorioAsignado'");
    }

    @Override
    public void updateConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateConsultorioAsignado'");
    }

    @Override
    public void deleteConsultorioAsignado(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteConsultorioAsignado'");
    }

    @Override
    public ConsultorioAsignadoResponseDto getConsultorioAsignadoById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConsultorioAsignadoById'");
    }
    
}
