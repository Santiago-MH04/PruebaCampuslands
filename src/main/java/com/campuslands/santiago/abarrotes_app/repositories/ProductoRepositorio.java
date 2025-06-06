package com.campuslands.santiago.abarrotes_app.repositories;

import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.utils.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    //Atributos de ProductoRepositorio
    //Constructores de ProductoRepositorio
    //Asignadores de atributos de ProductoRepositorio (setters)
    //Lectores de atributos de ProductoRepositorio (getters)
        //Métodos de ProductoRepositorio
    @Query("SELECT p FROM Producto p WHERE p.categoria = :1")
    public List<Producto> findByCategoria(String categoria);
}
