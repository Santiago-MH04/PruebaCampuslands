package com.campuslands.santiago.abarrotes_app.services.abstractions;

import com.campuslands.santiago.abarrotes_app.DTOs.VentaDTO;
import com.campuslands.santiago.abarrotes_app.entities.Venta;

import java.util.List;

public interface ServicioVenta {
    //Atributos de ServicioVenta
    //Constructores de ServicioVenta
    //Asignadores de atributos de ServicioVenta (setters)
    //Lectores de atributos de ServicioVenta (getters)
        //Métodos de ServicioVenta
    List<Venta> findAll();
    List<Venta> findByIdentificadorCliente(String clienteID);
    Venta register(VentaDTO ventaDTO);
}
