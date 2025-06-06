package com.campuslands.santiago.abarrotes_app.controllers;

import com.campuslands.santiago.abarrotes_app.DTOs.VentaDTO;
import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.Venta;
import com.campuslands.santiago.abarrotes_app.exceptions.VentaException;
import com.campuslands.santiago.abarrotes_app.services.abstractions.ServicioVenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ventas")
public class VentaController {
        //Atributos de VentaController
    private final ServicioVenta servicioVenta;

        //Constructores de VentaController
    public VentaController(ServicioVenta servicioVenta) {
        this.servicioVenta = servicioVenta;
    }

    //Asignadores de atributos de VentaController (setters)
    //Lectores de atributos de VentaController (getters)
        //Métodos de VentaController
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Venta> findAll(){
        return this.servicioVenta.findAll();
    }

    @GetMapping("/cliente/{identificadorCliente}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Venta> findByIdentificadorCliente(@PathVariable String identificadorCliente){
        return this.servicioVenta.findByIdentificadorCliente(identificadorCliente);
    }

    @PostMapping
    public ResponseEntity<Venta> registerVenta(VentaDTO ventaDTO){
        Venta venta = this.servicioVenta.register(ventaDTO);
        if(venta.getId() != null){
            return ResponseEntity.ok(venta);
        }
        throw new VentaException("No se puede registrar la venta, por falta de stock");
    }
}
