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
    public ConsultorioAsignado() {
    }

    //private Consultorio consultorio;


}
