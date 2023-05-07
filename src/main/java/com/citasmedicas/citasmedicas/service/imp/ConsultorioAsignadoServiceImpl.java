package com.citasmedicas.citasmedicas.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioDoesntExistException;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioReservadoAlreadyExistException;
import com.citasmedicas.citasmedicas.exceptions.DoctorDoesntExistExceptions;
import com.citasmedicas.citasmedicas.exceptions.EspecialidadDoesntExistExceptions;

import com.citasmedicas.citasmedicas.model.entity.Consultorio;
import com.citasmedicas.citasmedicas.model.entity.ConsultorioAsignado;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;

import com.citasmedicas.citasmedicas.model.repository.ConsultorioAsignadoRepository;

import com.citasmedicas.citasmedicas.service.ConsultorioAsignadoService;
import com.citasmedicas.citasmedicas.service.ConsultorioService;
import com.citasmedicas.citasmedicas.service.DoctorService;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

@Service
public class ConsultorioAsignadoServiceImpl implements ConsultorioAsignadoService {

    private final ConsultorioAsignadoRepository consultorioAsignadoRepository;
    @Autowired
    private ConsultorioService consultorioService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private EspecialidadesService especialidadService;


    public ConsultorioAsignadoServiceImpl(ConsultorioAsignadoRepository consultorioAsignadoRepository) {
        this.consultorioAsignadoRepository = consultorioAsignadoRepository;

    }

    @Override
    public List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados() {
        try {
            List<ConsultorioAsignado> consultorioAsignados = consultorioAsignadoRepository.findAll();
            return consultorioAsignados.stream().map(
                    consAsign -> new ConsultorioAsignadoResponseDto(consAsign.getDoctor().getId(),
                            consAsign.getDoctor().getNombre(), consAsign.getDoctor().getApellido(),
                            consAsign.getDoctor().getCorreo(), consAsign.getDoctor().getEspecialidad().getNombre(),
                            consAsign.getConsultorio().getId(), consAsign.getConsultorio().getCiudad(),
                            consAsign.getConsultorio().getDireccion(), consAsign.getConsultorio().getNumero(),
                            consAsign.getConsultorio().getDescripcion(), consAsign.getId(),
                            consAsign.getInicioReserva(), consAsign.getFinReserva()))
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new UnsupportedOperationException("Unimplemented method 'getConsultoriosAsignados'" + ex);
        }
    }

    @Override
    public void createConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto) {
        try {
            // validar si el consultorio en la fecha de reserva que entran ya existe
            ConsultorioDto consultDto = consultorioService
                    .getConsultorioById(consultorioAsignadoRequestDto.getId_consultorio());

            DoctorResponseDto doctorDto = doctorService.getDoctoresById(consultorioAsignadoRequestDto.getId_doctor());
            EspecialidadDto especialidadDto = especialidadService
                    .getEspecialidadByNombre(doctorDto.getNombreEspecialidad());

            List<ConsultorioAsignado> consultAsig = consultorioAsignadoRepository
                    .findAllByInicioReservaLessThanEqualAndFinReservaGreaterThanEqual(
                            consultorioAsignadoRequestDto.getInicioReserva(),
                            consultorioAsignadoRequestDto.getFinReserva());
            if (consultAsig.size() > 0) {
                throw new ConsultorioReservadoAlreadyExistException(
                        "No se puede reservar, ya existe una reserva en ese rango de fechas");
            }
            ConsultorioAsignado consultAsigDb = new ConsultorioAsignado();
            consultAsigDb.setInicioReserva(consultorioAsignadoRequestDto.getInicioReserva());
            consultAsigDb.setFinReserva(consultorioAsignadoRequestDto.getFinReserva());
            consultAsigDb.setConsultorio(new Consultorio(consultDto.getId(), consultDto.getCiudad(),
                    consultDto.getDireccion(), consultDto.getNumero(), consultDto.getDescripcion()));
            /* Asignando DOCTOR, validar si se puede hacer de mejor forma */
            Especialidad especialidadAsigDb = new Especialidad(especialidadDto.getId(),
                    EnumEspecialidad.valueOf(especialidadDto.getNombre()));
            Doctor doctorAsigDb = new Doctor(doctorDto.getIdDoctor(), doctorDto.getNombre(), doctorDto.getApellido(),
                    doctorDto.getCorreo(), especialidadAsigDb);
            consultAsigDb.setDoctor(doctorAsigDb);
            consultAsigDb = consultorioAsignadoRepository.save(consultAsigDb);

            // Optional <ConsultorioAsignado>
            // consultAsigOpc=ConsultorioAsignadoRepository.findByidConsultorio(Long id);
        } catch (ConsultorioDoesntExistException ex) {
            throw new ConsultorioDoesntExistException("El consultorio ingresado no existe" + ex);
        } catch (DoctorDoesntExistExceptions ex) {
            throw new DoctorDoesntExistExceptions("El doctor ingresado no existe");
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error en create consultorio " + ex);
        }
    }


    @Override
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByConsultorio(Long id) {
        try {
            // buscar si el id del consultorio existe
            ConsultorioDto consultorioOp = consultorioService.getConsultorioById(id);

            List<ConsultorioAsignado> consultorioAsignados = consultorioAsignadoRepository
                    .findAllByConsultorioId(consultorioOp.getId());
            return consultorioAsignados.stream().map(
                    consAsign -> new ConsultorioAsignadoResponseDto(consAsign.getDoctor().getId(),
                            consAsign.getDoctor().getNombre(), consAsign.getDoctor().getApellido(),
                            consAsign.getDoctor().getCorreo(), consAsign.getDoctor().getEspecialidad().getNombre(),
                            consAsign.getConsultorio().getId(), consAsign.getConsultorio().getCiudad(),
                            consAsign.getConsultorio().getDireccion(), consAsign.getConsultorio().getNumero(),
                            consAsign.getConsultorio().getDescripcion(), consAsign.getId(),
                            consAsign.getInicioReserva(), consAsign.getFinReserva()))
                    .collect(Collectors.toList());
        } catch (ConsultorioDoesntExistException ex) {
            throw new ConsultorioDoesntExistException("Consultorio no existe");
        } catch (RuntimeException ex) {
            throw new UnsupportedOperationException("ERROR EN CONSULTORIO ASIGNADO'" + ex);
        }
    }

    @Override
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByEspecialidad(String especialidad) {
        try {

            // trae uunicamente los consuktoriosAsingados que tengan esa especialidad
            EspecialidadDto especialidadOp = especialidadService.getEspecialidadByNombre(especialidad.toUpperCase());
            EnumEspecialidad enumEspecialidad = EnumEspecialidad.valueOf(especialidadOp.getNombre());

            List<ConsultorioAsignado> consultorioAsignados = consultorioAsignadoRepository
                    .findAllByDoctorEspecialidad(new Especialidad(especialidadOp.getId(), enumEspecialidad));
            return consultorioAsignados.stream().map(
                    consAsign -> new ConsultorioAsignadoResponseDto(consAsign.getDoctor().getId(),
                            consAsign.getDoctor().getNombre(), consAsign.getDoctor().getApellido(),
                            consAsign.getDoctor().getCorreo(), consAsign.getDoctor().getEspecialidad().getNombre(),
                            consAsign.getConsultorio().getId(), consAsign.getConsultorio().getCiudad(),
                            consAsign.getConsultorio().getDireccion(), consAsign.getConsultorio().getNumero(),
                            consAsign.getConsultorio().getDescripcion(), consAsign.getId(),
                            consAsign.getInicioReserva(), consAsign.getFinReserva()))
                    .collect(Collectors.toList());

        } catch (EspecialidadDoesntExistExceptions ex) {
            throw new EspecialidadDoesntExistExceptions("La especialidad no existe " + ex);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error al consultar por especialidad"+ especialidad +" " + ex);
        }

    }

    
    @Override //PDT
    public List<ConsultorioAsignadoResponseDto> findAllByBetweenAsignedDates(LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {
        try {

            return null;
        } catch (RuntimeException ex) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'findAllByBetweenAsignedDates'");
        }
    }

    @Override //PDT
    public ConsultorioAsignadoResponseDto getConsultorioAsignadoById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConsultorioAsignadoById'");
    }

    @Override //PDT
    public void deleteConsultorioAsignado(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteConsultorioAsignado'");
    }
    
    @Override //PDT 
    public void updateConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateConsultorioAsignado'");
    }
}
