package com.campuslands.santiago.abarrotes_app.repositories;

import com.campuslands.santiago.abarrotes_app.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long> {
    //Atributos de VentaRepositorio
    //Constructores de VentaRepositorio
    //Asignadores de atributos de VentaRepositorio (setters)
    //Lectores de atributos de VentaRepositorio (getters)
    //Métodos de VentaRepositorio
}
