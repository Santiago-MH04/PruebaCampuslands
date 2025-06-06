package com.campuslands.santiago.abarrotes_app.services.implementations;

import com.campuslands.santiago.abarrotes_app.DTOs.VentaDTO;
import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.Venta;
import com.campuslands.santiago.abarrotes_app.exceptions.VentaException;
import com.campuslands.santiago.abarrotes_app.repositories.ProductoRepositorio;
import com.campuslands.santiago.abarrotes_app.repositories.VentaRepositorio;
import com.campuslands.santiago.abarrotes_app.services.abstractions.ServicioVenta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Venta> findAll() {
        return this.repoVenta.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findByIdentificadorCliente(String clienteID) {
        return this.repoVenta.findByidentificadorCliente(clienteID);
    }

    /*@Override
    @Transactional
    public Venta register(VentaDTO ventaDTO) {
        if(this.seAutorizaVenta(ventaDTO.getProducto().getId(), ventaDTO.getCantidadComprada())){
                // Actualizar las existencias del producto
            Producto producto = ventaDTO.getProducto();
                producto.setCantidadEnStock(producto.   getCantidadEnStock() - ventaDTO.getCantidadComprada());
            Producto productoNuevo = this.repoProducto.save(producto);

                //Registar la venta
            return this.repoVenta.save(
                Venta.builder()
                    .identificadorCliente(ventaDTO.getIdentificadorCliente())
                    .producto(productoNuevo)
                    .cantidadComprada(ventaDTO.getCantidadComprada())
                    .build()
            );
        }
        return new Venta();
    }*/

    @Override
    @Transactional
    public Venta register(VentaDTO ventaDTO) throws VentaException{
            // Buscar el producto en la base de datos antes de modificarlo
        Producto producto = repoProducto.findById(ventaDTO.getProducto().getId())
                .orElseThrow(() -> new VentaException("Producto no encontrado para facturar"));

            // Verificar si la venta puede autorizarse
        if (!seAutorizaVenta(producto.getId(), ventaDTO.getCantidadComprada())) {
            throw new VentaException("No se puede registrar la venta, por falta de stock");
        }
            // Actualizar stock del producto
        producto.setCantidadEnStock(producto.getCantidadEnStock() - ventaDTO.getCantidadComprada());

            // Registrar la venta con el producto actualizado
        return this.repoVenta.save(Venta.builder()
                .identificadorCliente(ventaDTO.getIdentificadorCliente())
                .producto(this.repoProducto.save(producto))
                .cantidadComprada(ventaDTO.getCantidadComprada())
                .build());
    }


    private boolean seAutorizaVenta(Long id, Integer cantidad){
        if(this.repoProducto.findById(id).isPresent()){
            return this.repoProducto.findById(id).get().getCantidadEnStock() >= cantidad;
        }
        return false;
    }
}
