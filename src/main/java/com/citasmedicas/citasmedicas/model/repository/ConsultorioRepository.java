package com.citasmedicas.citasmedicas.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citasmedicas.citasmedicas.model.entity.Consultorio;

public interface ConsultorioRepository extends JpaRepository<Consultorio,Long> {
    List<Consultorio> findAll();
    Optional<Consultorio> findById(Long id);
    List<Consultorio> findByDireccion(String direccion);
    //Consulta que ayuda a validar que sea exactamente el mismo consultorio
    List<Consultorio> findByCiudadAndDescripcionAndDireccionAndNumero(String ciudad,String descripcion,String direccion,Integer numero);


    


}
