package com.citasmedicas.citasmedicas.controller.dto;

import java.time.LocalDateTime;

public class ConsultorioAsignadoRequestDto {
    private Long id;
    private LocalDateTime inicioReserva;
    private LocalDateTime finReserva;
    private Long id_doctor;
    private Long id_consultorio;
    private Long id_citaMedica;
    
    public ConsultorioAsignadoRequestDto() {
    }

    public ConsultorioAsignadoRequestDto(Long id, LocalDateTime inicioReserva, LocalDateTime finReserva, Long id_doctor,
            Long id_consultorio) {
        this.id = id;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.id_doctor = id_doctor;
        this.id_consultorio = id_consultorio;
    }

    public ConsultorioAsignadoRequestDto(Long id, LocalDateTime inicioReserva, LocalDateTime finReserva, Long id_doctor,
            Long id_consultorio, Long id_citaMedica) {
        this.id = id;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.id_doctor = id_doctor;
        this.id_consultorio = id_consultorio;
        this.id_citaMedica = id_citaMedica;
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

    public Long getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(Long id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Long getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(Long id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public Long getId_citaMedica() {
        return id_citaMedica;
    }

    public void setId_citaMedica(Long id_citaMedica) {
        this.id_citaMedica = id_citaMedica;
    }

    

}
