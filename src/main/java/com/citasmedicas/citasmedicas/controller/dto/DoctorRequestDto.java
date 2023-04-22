package com.citasmedicas.citasmedicas.controller.dto;

public class DoctorRequestDto {
    
    private long Id;
    private String nombre;
    private String apellido;
    private String correo;
    private EspecialidadDto especialidad;

    
    public DoctorRequestDto() {
    }


    public DoctorRequestDto(long id, String nombre, String apellido, String correo, EspecialidadDto especialidad) {
        Id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.especialidad = especialidad;
    }


    public long getId() {
        return Id;
    }


    public void setId(long id) {
        Id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public EspecialidadDto getEspecialidad() {
        return especialidad;
    }


    public void setEspecialidad(EspecialidadDto especialidad) {
        this.especialidad = especialidad;
    }



    

}
