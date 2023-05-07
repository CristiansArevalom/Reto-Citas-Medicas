package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.DoctorRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.exceptions.DoctorDoesntExistExceptions;
import com.citasmedicas.citasmedicas.exceptions.EspecialidadDoesntExistExceptions;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.repository.DoctorRepository;
import com.citasmedicas.citasmedicas.service.DoctorService;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    // llamo el servicio de especialidade ya que necesito traer la especialidad x
    // nombre
    private EspecialidadesService especialidadesService;
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;

    }

    @Override // trae los doctores con su especialidad
    public List<DoctorResponseDto> getDoctores() {
        try {
            List<Doctor> doctores = doctorRepository.findAllWithEspecialidad();
            // select * from doctores d inner join especialidades es on d.id_especialidad =
            // es.id; List<Doctor> doctores = doctorRepository.findAll();
            // llenando DTO doctores
            return doctores.stream().map(doctor -> new DoctorResponseDto(doctor.getId(),
                    doctor.getNombre(),
                    doctor.getApellido(),
                    doctor.getCorreo(),
                    doctor.getEspecialidad().getNombre())).collect(Collectors.toList());

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("ERROR al buscar" + e.getStackTrace());
        }
    }

    @Override
    public void createDoctor(DoctorRequestDto doctor) {
        // TODO para crear un doctor 1 validar si ya existe.. ,
        try {
            Doctor doctorDb = new Doctor();
            doctorDb.setNombre(doctor.getNombre());
            doctorDb.setApellido(doctor.getApellido());
            doctorDb.setCorreo(doctor.getCorreo());
            // Obtener el nombre de la especialidad del DTO
            String nombreEspecialidad = doctor.getNombreEspecialidad();
            // Buscar la especialidad por nombre en la base de datos
            EspecialidadDto especialidadDto = especialidadesService.getEspecialidadByNombre(nombreEspecialidad);
            // Si no se encuentra la especialidad, lanzar una excepción o manejar el error
            if (especialidadDto == null) {
                throw new RuntimeException("La especialidad " + nombreEspecialidad + " no existe.");
            }
            // Como ya existe, se trae el EnumEspecialidad para poder crear la especialidad
            // Asignar la especialidad al Doctor
            doctorDb.setEspecialidad(
                    new Especialidad(especialidadDto.getId(), EnumEspecialidad.valueOf(especialidadDto.getNombre())));

            doctorDb = doctorRepository.save(doctorDb);
            /*
             * VALIDAR SI O SI DEBE EXISTIR EN LA BASE DE DATOS Y EN LA CLASE LA
             * ESPECIALIDAD
             */
        } catch (EspecialidadDoesntExistExceptions ex) { // atrapa el error en caso de que EnumEspecialidadValueOf no la
                                                         // tenga
            throw new EspecialidadDoesntExistExceptions("Error al crear doctor por la especialidad " + ex.getMessage());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error al crear doctor " + ex.getMessage());
        }

    }

    @Override
    public void updateDoctor(DoctorRequestDto doctor) {

        Optional<Doctor> doctorOp = doctorRepository.findById(doctor.getId());
        if (doctorOp.isEmpty()) {
            throw new DoctorDoesntExistExceptions("El doctor con ese id no existe");
        }
        Doctor doctorDb = doctorOp.get();
        doctorDb.setNombre(doctor.getNombre());
        doctorDb.setApellido(doctor.getApellido());
        doctorDb.setCorreo(doctor.getCorreo());
        // Obtener el nombre de la especialidad del DTO
        String nombreEspecialidad = doctor.getNombreEspecialidad();
        // Buscar la especialidad por nombre en la base de datos
        EspecialidadDto especialidadDto = especialidadesService.getEspecialidadByNombre(nombreEspecialidad);
        // Si no se encuentra la especialidad, lanzar una excepción o manejar el error
        if (especialidadDto == null) {
            throw new RuntimeException("La especialidad " + nombreEspecialidad + " no existe.");
        }
        // Como ya existe, se trae el EnumEspecialidad para poder crear la especialidad
        // Asignar la especialidad al Doctor
        doctorDb.setEspecialidad(
                new Especialidad(especialidadDto.getId(), EnumEspecialidad.valueOf(especialidadDto.getNombre())));
        doctorDb = doctorRepository.save(doctorDb);
    }

    @Override
    public void deleteDoctor(Long id) {
        Optional <Doctor> doctorOp = doctorRepository.findById(id);
        if(doctorOp.isEmpty()){
            throw new DoctorDoesntExistExceptions("El doctor no existe");
        }
        Doctor doctorDb=doctorOp.get();
        doctorRepository.delete(doctorDb);
    }

    @Override
    public DoctorResponseDto getDoctoresById(Long id) {
        Optional<Doctor> doctorOp = doctorRepository.findById(id);
        if (doctorOp.isEmpty()) {
            throw new DoctorDoesntExistExceptions("El doctor con ese Id no existe");
        }
        Doctor doctorDb = doctorOp.get();
        return new DoctorResponseDto(doctorDb.getId(), doctorDb.getNombre(), doctorDb.getApellido(),
                doctorDb.getCorreo(), doctorDb.getEspecialidad().getNombre());
    }

    
    @Override
    public List<DoctorResponseDto> getDoctoresByEspecialidad(String especialidad) {

        try {
            //buscar priemro si existe la especialidad en la clase especialidad y la crea
            EspecialidadDto especialidadOp = especialidadesService.getEspecialidadByNombre(especialidad);
            Especialidad especialidadDb = new Especialidad(especialidadOp.getId(), EnumEspecialidad.valueOf(especialidadOp.getNombre()));
        List<Doctor> doctores= doctorRepository.findAllByEspecialidad(especialidadDb);
            return doctores.stream().map(doctor -> new DoctorResponseDto(doctor.getId(),
            doctor.getNombre(),
            doctor.getApellido(),
            doctor.getCorreo(),
            doctor.getEspecialidad().getNombre())).collect(Collectors.toList());

        }catch(DoctorDoesntExistExceptions ex){
            throw new DoctorDoesntExistExceptions("No existe doctor con esa especialidad");
        }catch(RuntimeException ex){
            throw new RuntimeException(ex);
        }
    }

}
