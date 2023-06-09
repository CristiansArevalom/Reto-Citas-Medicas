package com.citasmedicas.citasmedicas.model.entity;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
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
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm") //@DateTimeFormat se utiliza para especificar cómo se deben analizar las fechas y horas en el lado del servidor cuando se reciben como entrada en la aplicación.
    private LocalDateTime inicioReserva;
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime finReserva;
    @ManyToOne
    @JoinColumn(name="id_doctor",foreignKey = @ForeignKey(name="CONSULT_ASIG_ID_DOCTOR_FK"))
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name="id_consultorio",foreignKey = @ForeignKey(name="CONSULT_ASIG_ID_CONSULTORIO_FK"))
    private Consultorio consultorio;
    public ConsultorioAsignado() {
    }

    public ConsultorioAsignado(Long id, LocalDateTime inicioReserva, LocalDateTime finReserva, Doctor doctor,
            Consultorio consultorio) {
        this.id = id;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.doctor = doctor;
        this.consultorio = consultorio;
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



}
