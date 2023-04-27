package com.citasmedicas.citasmedicas.model.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="Consultorios_asignados")
public class ConsultorioAsignado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime inicioReserva;
    private LocalDateTime finReserva;
    @ManyToOne
    @JoinColumn(name="id_doctor",foreignKey = @ForeignKey(name="CONSULT_ASIG_ID_DOCTOR_FK"))
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name="id_consultorio",foreignKey = @ForeignKey(name="CONSULT_ASIG_ID_CONSULTORIO_FK"))
    private Consultorio consultorio;
    @ManyToOne
    @JoinColumn(name="id_cita_medica",foreignKey = @ForeignKey(name="CONSULT_ASIG_ID_CITAMEDICA_FK"))
    private CitaMedica citaMedica;
    
    public ConsultorioAsignado() {
    }

    public ConsultorioAsignado(Long id, LocalDateTime inicioReserva, LocalDateTime finReserva, Doctor doctor,
            Consultorio consultorio, CitaMedica citaMedica) {
        this.id = id;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.doctor = doctor;
        this.consultorio = consultorio;
        this.citaMedica = citaMedica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(LocalDateTime inicioReserva) {
        this.inicioReserva = inicioReserva;
    }

    public LocalDateTime getFinReserva() {
        return finReserva;
    }

    public void setFinReserva(LocalDateTime finReserva) {
        this.finReserva = finReserva;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public CitaMedica getCitaMedica() {
        return citaMedica;
    }

    public void setCitaMedica(CitaMedica citaMedica) {
        this.citaMedica = citaMedica;
    }

    //private Consultorio consultorio;


}
