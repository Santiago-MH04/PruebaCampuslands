package com.campuslands.santiago.abarrotes_app.services.implementations;

import com.campuslands.santiago.abarrotes_app.DTOs.ProductoDTO;
import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.utils.Categoria;
import com.campuslands.santiago.abarrotes_app.repositories.ProductoRepositorio;
import com.campuslands.santiago.abarrotes_app.services.abstractions.ServicioProducto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProductoImpl implements ServicioProducto {
        //Atributos de ServicioProductoImpl
    private final ProductoRepositorio repoProducto;

        //Constructores de ServicioProductoImpl
    public ServicioProductoImpl(ProductoRepositorio repoProducto) {
        this.repoProducto = repoProducto;
    }

    //Asignadores de atributos de ServicioProductoImpl (setters)
    //Lectores de atributos de ServicioProductoImpl (getters)
        //Métodos de ServicioProductoImpl
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return this.repoProducto.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByCategoria(Categoria categoria) {
        return this.repoProducto.findByCategoria(String.valueOf(categoria));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return this.repoProducto.findById(id);
    }

    @Override
    @Transactional()
    public Producto create(ProductoDTO productoDTO) {
        return this.repoProducto.save(
            buildProducto(productoDTO)
        );
    }

    @Override
    @Transactional()
    public Optional<Producto> update(ProductoDTO productoDTO) {
        return this.repoProducto.findById(productoDTO.getId())
                .map(p -> this.repoProducto.save(buildProducto(productoDTO)));
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        this.repoProducto.deleteById(id);
    }

    private static Producto buildProducto(ProductoDTO productoDTO) {
        return Producto.builder()
                .nombre(productoDTO.getNombre())
                .sku(productoDTO.getSku())
                .precioUnitario(productoDTO.getPrecioUnitario())
                .cantidadEnStock(productoDTO.getCantidadEnStock())
                .nombreCategoria(productoDTO.getNombreCategoria())
                .build();
    }
}
