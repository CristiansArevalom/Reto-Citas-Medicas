package com.citasmedicas.citasmedicas.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Especialidades")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumEspecialidad enumEspecialidad; //los datos los debe traer de un ENUM 
    @Column(nullable = false)
    private String descripcion;//los datos los debe traer de un ENUM 

    public Especialidad() {
    }

    public Especialidad(Long id, EnumEspecialidad nombreEnumEspecialidad) {
        this.id = id;
        this.enumEspecialidad = nombreEnumEspecialidad;
        this.descripcion = nombreEnumEspecialidad.getDescripcion() ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre(){
        return enumEspecialidad.getNombreEnum();
    }

    public String getDescripcion() {
        return enumEspecialidad.getDescripcion();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        this.enumEspecialidad.setDescripcion(descripcion);
    }

}
