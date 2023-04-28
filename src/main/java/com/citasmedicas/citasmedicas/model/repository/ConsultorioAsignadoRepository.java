package com.citasmedicas.citasmedicas.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citasmedicas.citasmedicas.model.entity.ConsultorioAsignado;

public interface ConsultorioAsignadoRepository extends JpaRepository<ConsultorioAsignado,Long>{
    //validar si consultorio a reservar ya esta reservado en base a fecha inicio y fin
    //
    //toco con JPA

    //consultar consultorios asignados (traer doctor, especialidad y detalles del consultorio)
    List<ConsultorioAsignado> findAll();
    List<ConsultorioAsignado> findAllByConsultorioId(Long id);
    List<ConsultorioAsignado> findAllByDoctorId(Long id);
    List<ConsultorioAsignado> findAllByInicioReservaLessThanEqualAndFinReservaGreaterThanEqual(LocalDateTime inicioReserva,LocalDateTime finReserva);
    /*
    @Query("SELECT conAsign FROM ConsultorioAsignado conAsign"
    +"INNER JOIN Doctor doc ON conAsign.Doctor = doc")
    List<ConsultorioAsignado> findAllWithDoctorAndconsultorioInfo();
    */
    /*select doc.nombre,doc.apellido,esp.nombre as ESPECIALIDAD,const.direccion,const.numero AS 'numero consultorio',const.ciudad,conAsing.inicio_reserva,conAsing.fin_reserva
from consultorios_asignados conAsing inner join consultorios const on conAsing.id_consultorio = const.id
inner join doctores doc on conAsing.id_doctor = doc.id
inner join especialidades esp on doc.id_especialidad = esp.id; */
    
    
}
