package com.campuslands.santiago.abarrotes_app.services.abstractions;

import com.campuslands.santiago.abarrotes_app.DTOs.ProductoDTO;
import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.utils.Categoria;

import java.util.List;
import java.util.Optional;

public interface ServicioProducto {
    //Atributos de ServicioProducto
    //Constructores de ServicioProducto
    //Asignadores de atributos de ServicioProducto (setters)
    //Lectores de atributos de ServicioProducto (getters)
        //Métodos de ServicioProducto
    List<Producto> findAll();
    List<Producto> findByCategoria(Categoria categoria);
    Optional<Producto> findById(Long id);
    Producto create(ProductoDTO productoDTO);
    Optional<Producto> update(ProductoDTO productoDTO);
    void deleteById(Long id);
}
