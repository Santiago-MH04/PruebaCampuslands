package com.campuslands.santiago.abarrotes_app.repositories;

import com.campuslands.santiago.abarrotes_app.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    //Atributos de ProductoRepositorio
    //Constructores de ProductoRepositorio
    //Asignadores de atributos de ProductoRepositorio (setters)
    //Lectores de atributos de ProductoRepositorio (getters)
    //Métodos de ProductoRepositorio
}
