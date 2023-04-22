package com.citasmedicas.citasmedicas.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//https://www.baeldung.com/jpa-persisting-enums-in-jpa
//https://www.oscarblancarteblog.com/2016/11/14/mapeo-enumeraciones-enumerated/
@Entity
@Table(name = "Especialidades")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumEspecialidad nombre; //los datos los debe traer de un ENUM 
    @Column(nullable = false)
    private String descripcion;//los datos los debe traer de un ENUM 

    public Especialidad() {
    }

    public Especialidad(Long id, EnumEspecialidad nombreEnumEspecialidad) {
        this.id = id;
        this.nombre = nombreEnumEspecialidad;
        this.descripcion = nombreEnumEspecialidad.getDescripcion() ;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre(){
        return nombre.getNombreEnum();
    }

    public String getDescripcion() {
        return nombre.getDescripcion();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        this.nombre.setDescripcion(descripcion);
    }


}
