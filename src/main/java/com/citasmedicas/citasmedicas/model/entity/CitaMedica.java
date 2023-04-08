package com.citasmedicas.citasmedicas.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="citas_medicas")
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_doctor",foreignKey = @ForeignKey(name="CITA_MED_ID_DOCTOR_FK"))
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name="id_paciente",foreignKey = @ForeignKey(name="CITA_MED_ID_PACIENTE_FK"))
    private Paciente paciente;
    @Column(nullable = false)
    private String especialidad;  //ESTABLECER LOGICA PARA QUE ESTO SE LLENDE DE LO QUE TRAIGA EL DOCTOR
    @Column(nullable = false)
    private LocalDateTime fechaInicio;
    @Column(nullable = false)
    private LocalDateTime fechaFin;
    @ManyToOne
    @JoinColumn(name="id_consultorio",foreignKey = @ForeignKey(name="CITA_MED_ID_CONSULTORIO_FK"))
    private Consultorio consultorio;

    public CitaMedica() {
    }

    public CitaMedica(Long id, Doctor doctor, Paciente paciente, String especialidad, LocalDateTime fechaInicio,
            LocalDateTime fechaFin, Consultorio consultorio) {
        this.id = id;
        this.doctor = doctor;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.consultorio = consultorio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    
}
