package com.campuslands.santiago.abarrotes_app.services.implementations;

import com.campuslands.santiago.abarrotes_app.DTOs.VentaDTO;
import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.Venta;
import com.campuslands.santiago.abarrotes_app.repositories.ProductoRepositorio;
import com.campuslands.santiago.abarrotes_app.repositories.VentaRepositorio;
import com.campuslands.santiago.abarrotes_app.services.abstractions.ServicioVenta;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioVentaImpl implements ServicioVenta {
        //Atributos de ServicioVentaImpl
    private final VentaRepositorio repoVenta;
    private final ProductoRepositorio repoProducto;

        //Constructores de ServicioVentaImpl
    public ServicioVentaImpl(VentaRepositorio repoVenta, ProductoRepositorio repoProducto) {
        this.repoVenta = repoVenta;
        this.repoProducto = repoProducto;
    }

    //Asignadores de atributos de ServicioVentaImpl (setters)
    //Lectores de atributos de ServicioVentaImpl (getters)
        //Métodos de ServicioVentaImpl
    @Override
    public List<Venta> findAll() {
        return this.repoVenta.findAll();
    }

    @Override
    public List<Venta> findByIdentificadorCliente(String clienteID) {
        return this.repoVenta.findByidentificadorCliente(clienteID);
    }

    @Override
    public Venta register(VentaDTO ventaDTO) {
        if(this.seAutorizaVenta(ventaDTO.getProducto().getId(), ventaDTO.getCantidadComprada())){
                // Actualizar las existencias del producto
            Producto producto = ventaDTO.getProducto();
                producto.setCantidadEnStock(producto.getCantidadEnStock() - ventaDTO.getCantidadComprada());
            this.repoProducto.save(producto);

                //Registar la venta
            return this.repoVenta.save(
                Venta.builder()
                    .identificadorCliente(ventaDTO.getIdentificadorCliente())
                    .producto(ventaDTO.getProducto())
                    .cantidadComprada(ventaDTO.getCantidadComprada())
                    .montoTotalVenta(ventaDTO.getMontoTotalVenta())
                    .fechaVenta(LocalDateTime.now())
                    .build()
            );
        }
        return new Venta();
    }

    private boolean seAutorizaVenta(Long id, Integer cantidad){
        if(this.repoProducto.findById(id).isPresent()){
            return this.repoProducto.findById(id).get().getCantidadEnStock() >= cantidad;
        }
        return false;
    }
}
