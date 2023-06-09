package com.citasmedicas.citasmedicas.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;
import com.citasmedicas.citasmedicas.exceptions.CitaMedicaException;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioDoesntExistException;
import com.citasmedicas.citasmedicas.model.entity.CitaMedica;
import com.citasmedicas.citasmedicas.model.entity.Consultorio;
import com.citasmedicas.citasmedicas.model.entity.ConsultorioAsignado;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.entity.Paciente;
import com.citasmedicas.citasmedicas.model.repository.CitaMedicaRepository;
import com.citasmedicas.citasmedicas.service.CitasMedicasService;
import com.citasmedicas.citasmedicas.service.ConsultorioAsignadoService;
import com.citasmedicas.citasmedicas.service.ConsultorioService;
import com.citasmedicas.citasmedicas.service.DoctorService;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;
import com.citasmedicas.citasmedicas.service.PacienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CitasMedicasServiceImpl implements CitasMedicasService {
    private static final Logger logger = LoggerFactory.getLogger(CitasMedicasServiceImpl.class);
    private CitaMedicaRepository citaMedicaRepository;
    @Autowired
    private ConsultorioAsignadoService consultorioAsignadoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EspecialidadesService especialidadesService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    ConsultorioService consultorioService;

    public CitasMedicasServiceImpl(CitaMedicaRepository citaMedicaRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
    }

    @Override
    public List<CitaMedicaResponseDto> getCitasMedias() {
        try {
            List<CitaMedica> citasMedicasDb = citaMedicaRepository.findAll();
            return citasMedicasDb.stream().map(citaMedicaDb -> new CitaMedicaResponseDto(citaMedicaDb.getId(),
                    citaMedicaDb.getDoctor().getNombre() + " " + citaMedicaDb.getDoctor().getApellido(),
                    citaMedicaDb.getPaciente().getNombre() + " " + citaMedicaDb.getPaciente().getApellido(),
                    citaMedicaDb.getDoctor().getEspecialidad().getNombre(), citaMedicaDb.getFechaInicio(),
                    citaMedicaDb.getFechaFin(),
                    citaMedicaDb.getConsultorioAsignado().getConsultorio().getNumero().toString()))
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new CitaMedicaException("Error al obtener las citas medicas " + ex);
        }

    }
    @Override
    public CitaMedicaResponseDto createCitaMedica(CitaMedicaRequestDto citaMedicaRequestDto) {
        try {
            PacienteResponseDto pacienteDto = pacienteService
                    .getPacienteByCedula(citaMedicaRequestDto.getCedulaPaciente());

            List<ConsultorioAsignadoResponseDto> consutoriosConEspecialidad = consultorioAsignadoService
                    .getConsultorioAsignadoByEspecialidad(citaMedicaRequestDto.getEspecialidad());

            if (consutoriosConEspecialidad.size() == 0) {
                throw new ConsultorioDoesntExistException("No existen consultorios Asignados con esa especialidad");
            }
            if (!citaMedicaRequestDto.getFechaFin().isAfter(citaMedicaRequestDto.getFechaInicio())) {
                throw new CitaMedicaException(
                        "La fecha de inicio no puede ser superior a la fecha fin de reserva o a la actual");
            }
            List<ConsultorioAsignadoResponseDto> consutoriosConEspecialidadDisponibles = getConsultoriosDisponible(
                    consutoriosConEspecialidad, citaMedicaRequestDto.getFechaInicio(),
                    citaMedicaRequestDto.getFechaFin());
            if (consutoriosConEspecialidadDisponibles.size() == 0) {
                throw new CitaMedicaException(
                        "No existen consultorios disponibles para citas en el rango de fechas indicado");
            }
            // se escoje el 1er consultorioDisponible que ya este asignado a un Doctor x
            // defecto.
            ConsultorioAsignadoResponseDto consultorioAsigCita = consutoriosConEspecialidadDisponibles.get(0);

            // se vuelven a crear todos los datos, pasando del DTO a la entidad VALIDAAAR
            Paciente pacienteDb = new Paciente(pacienteDto.getId(), pacienteDto.getNombre(), pacienteDto.getApellido(),
                    pacienteDto.getCedula(), pacienteDto.getEdad(), pacienteDto.getTelefono());

            DoctorResponseDto doctorDto = doctorService.getDoctoresById(consultorioAsigCita.getIdDoctor());

            EspecialidadDto especialidadDto = especialidadesService
                    .getEspecialidadByNombre(doctorDto.getNombreEspecialidad());
            Especialidad especialidadDb = new Especialidad(especialidadDto.getId(),
                    EnumEspecialidad.valueOf(especialidadDto.getNombre()));

            ConsultorioDto consultorioDto = consultorioService
                    .getConsultorioById(consultorioAsigCita.getIdConsultorio());
            Consultorio consultorioDb = new Consultorio(consultorioDto.getId(), consultorioDto.getCiudad(),
                    consultorioDto.getDireccion(), consultorioDto.getNumero(), consultorioDto.getDescripcion());

            Doctor doctorDb = new Doctor(doctorDto.getIdDoctor(), doctorDto.getNombre(), doctorDto.getApellido(),
                    doctorDto.getCorreo(), especialidadDb);

            ConsultorioAsignado consultorioAsignadoDb = new ConsultorioAsignado(
                    consultorioAsigCita.getIdConsultorioAsignado(), consultorioAsigCita.getInicioReserva(),
                    consultorioAsigCita.getFinReserva(), doctorDb, consultorioDb);

            CitaMedica citaMedicaDb = new CitaMedica(doctorDb, pacienteDb, doctorDb.getEspecialidad().getNombre(),
                    citaMedicaRequestDto.getFechaInicio(), citaMedicaRequestDto.getFechaFin(), consultorioAsignadoDb);

            citaMedicaDb = citaMedicaRepository.save(citaMedicaDb);

            return new CitaMedicaResponseDto(citaMedicaDb.getId(),
                    citaMedicaDb.getDoctor().getNombre() + " " + citaMedicaDb.getDoctor().getApellido(),
                    citaMedicaDb.getPaciente().getNombre() + " " + citaMedicaDb.getPaciente().getApellido(),
                    citaMedicaDb.getDoctor().getEspecialidad().getNombre(), citaMedicaDb.getFechaInicio(),
                    citaMedicaDb.getFechaFin(),
                    citaMedicaDb.getConsultorioAsignado().getConsultorio().getNumero().toString());

        } catch (RuntimeException ex) {
            throw new CitaMedicaException("Error al guardar las citas medicas" + ex);
        }

    }

    @Override
    public CitaMedicaResponseDto getCitasMedicasById(Long id) {
        Optional<CitaMedica> citaMedicaOp = citaMedicaRepository.findById(id);
        if (citaMedicaOp.isEmpty()) {
            throw new CitaMedicaException("La cita medica indicada no exise");
        }
        CitaMedica citaMedicaDb = citaMedicaOp.get();

        return new CitaMedicaResponseDto(citaMedicaDb.getId(),
                citaMedicaDb.getDoctor().getNombre() + " " + citaMedicaDb.getDoctor().getApellido(),
                citaMedicaDb.getPaciente().getNombre() + " " + citaMedicaDb.getPaciente().getApellido(),
                citaMedicaDb.getDoctor().getEspecialidad().getNombre(), citaMedicaDb.getFechaInicio(),
                citaMedicaDb.getFechaFin(),
                citaMedicaDb.getConsultorioAsignado().getConsultorio().getNumero().toString());

    }

    @Override
    public List<CitaMedicaResponseDto> getCitasMedicasByConsultorioAsignadoId(Long id) {
        try {

            List<CitaMedica> citasMedicasDb = citaMedicaRepository.findAllByConsultorioAsignadoId(id);

            return citasMedicasDb.stream().map(citaMedicaDb -> new CitaMedicaResponseDto(citaMedicaDb.getId(),
                    citaMedicaDb.getDoctor().getNombre() + " " + citaMedicaDb.getDoctor().getApellido(),
                    citaMedicaDb.getPaciente().getNombre() + " " + citaMedicaDb.getPaciente().getApellido(),
                    citaMedicaDb.getDoctor().getEspecialidad().getNombre(), citaMedicaDb.getFechaInicio(),
                    citaMedicaDb.getFechaFin(),
                    citaMedicaDb.getConsultorioAsignado().getConsultorio().getNumero().toString()))
                    .collect(Collectors.toList());

        } catch (ConsultorioDoesntExistException ex) {
            throw new ConsultorioDoesntExistException("E" + ex);

        }
    }

    private List<ConsultorioAsignadoResponseDto> getConsultoriosDisponible(
            List<ConsultorioAsignadoResponseDto> consutoriosConEspecialidad, LocalDateTime fechaSolicitudCitaInicio,
            LocalDateTime fechaSolicitudCitaFin) {
        /*
         * dado un listado de consuktorios, debe retornar los que la fecha de inicio y
         * fin solicitud este dentro del rango de fecha resrva
         * y si tienen una cita medica, esta no debe cruzarse con la fecha inicio y fin
         * de CONSULTORIO ASIGNADO
         */
        List<ConsultorioAsignadoResponseDto> consultoriosDisponibles = new ArrayList<>();

        for (ConsultorioAsignadoResponseDto consultorioAsig : consutoriosConEspecialidad) {
            LocalDateTime fechaInicioReserva = consultorioAsig.getInicioReserva();
            LocalDateTime fechaFinReserva = consultorioAsig.getFinReserva();
            // Verifica si la fecha de inicio de la solicitud de cita se superpone con una
            // fecha de reserva existente.
            boolean fechaInicioReservaOcupada = ((fechaSolicitudCitaInicio.isEqual(fechaInicioReserva)
                    || fechaSolicitudCitaInicio.isAfter(fechaInicioReserva))
                    && (fechaSolicitudCitaInicio.isEqual(fechaFinReserva)
                            || fechaSolicitudCitaInicio.isBefore(fechaFinReserva)));
            // Verifica si la fecha de finalización de la solicitud de cita se superpone con
            // una fecha de reserva existente.
            boolean fechaFinReservaOcupada = ((fechaSolicitudCitaFin.isEqual(fechaInicioReserva)
                    || fechaSolicitudCitaFin.isAfter(fechaInicioReserva))
                    && (fechaSolicitudCitaFin.isEqual(fechaFinReserva)
                            || fechaSolicitudCitaFin.isBefore(fechaFinReserva)));
            // Valida si las dos fechas estan ocupadas
            boolean fechaReservaOcupada = fechaInicioReservaOcupada || fechaFinReservaOcupada;
            if (fechaReservaOcupada) {
                boolean consultorioConCitaDisponible = true;
                List<CitaMedicaResponseDto> citasAsignadas = this
                        .getCitasMedicasByConsultorioAsignadoId(consultorioAsig.getIdConsultorioAsignado());
                // Iterar a través de cada cita asignada.
                for (CitaMedicaResponseDto citaAsignada : citasAsignadas) {
                    // Obtener la fecha de inicio y finalización de la cita asignada.
                    LocalDateTime fechaInicioAsignada = citaAsignada.getFechaInicio();
                    LocalDateTime fechaFinAsignada = citaAsignada.getFechaFin();

                    // Verificar si la fecha de inicio de la solicitud de cita se superpone con una
                    // fecha de reserva existente.
                    boolean fechaInicioOcupada = fechaSolicitudCitaInicio.isBefore(fechaFinAsignada)
                            && fechaFinAsignada.isAfter(fechaInicioAsignada);

                    // Verificar si la fecha de finalización de la solicitud de cita se superpone
                    // con una fecha de reserva existente.
                    boolean fechaFinOcupada = fechaSolicitudCitaFin.isBefore(fechaFinAsignada)
                            && fechaFinAsignada.isAfter(fechaInicioAsignada);

                    // Combinar las dos verificaciones booleanas anteriores en una sola verificación
                    // booleana.
                    boolean fechaCitaOcupada = fechaInicioOcupada || fechaFinOcupada;
                    logger.error(
                            "LOGGGOGOGOG" + fechaReservaOcupada + " " + fechaInicioOcupada + " " + fechaFinOcupada);

                    // Si la verificación booleana es verdadera, establecer
                    // "consultorioConCitaDisponible" en falso y salir del bucle.
                    // Si "fechaCitaOcupada" es verdadero, entonces la fecha está ocupada.
                    if (fechaCitaOcupada) {
                        consultorioConCitaDisponible = false;
                        break;
                    }
                }
                if (fechaReservaOcupada && consultorioConCitaDisponible) {
                    consultoriosDisponibles.add(consultorioAsig);
                }
            }

        }

        return consultoriosDisponibles;

    }
}
