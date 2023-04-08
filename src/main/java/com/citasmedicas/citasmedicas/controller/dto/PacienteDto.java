package com.citasmedicas.citasmedicas.controller.dto;

public class PacienteDto {
    private String nombre;
    private String apellido;
    private Integer cedula;
    private String edad; //es porque el front no pide fecha nacimiento
    private String telefono;
    
    public PacienteDto() {
    }
    
    public PacienteDto(String nombre, String apellido, Integer cedula, String edad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.edad = edad;
        this.telefono = telefono;
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
    public Integer getCedula() {
        return cedula;
    }
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
