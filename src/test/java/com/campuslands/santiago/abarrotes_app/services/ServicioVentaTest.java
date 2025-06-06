package com.campuslands.santiago.abarrotes_app.services;

import com.campuslands.santiago.abarrotes_app.DTOs.VentaDTO;
import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.Venta;
import com.campuslands.santiago.abarrotes_app.exceptions.VentaException;
import com.campuslands.santiago.abarrotes_app.repositories.ProductoRepositorio;
import com.campuslands.santiago.abarrotes_app.repositories.VentaRepositorio;
import com.campuslands.santiago.abarrotes_app.services.abstractions.ServicioVenta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ServicioVentaTest {
        //Atributos de ServicioVentaTest
    @Mock
    private ProductoRepositorio repoProducto;
    @Mock
    private VentaRepositorio repoVenta;
    @InjectMocks
    private ServicioVenta servicioVenta;

    //Constructores de ServicioVentaTest
    //Asignadores de atributos de ServicioVentaTest (setters)
    //Lectores de atributos de ServicioVentaTest (getters)
        //Métodos de ServicioVentaTest
    @Test
    void testRegistrarVenta_ConStockDisponible() {
            // Simular datos
        Producto producto = new Producto();
            producto.setId(1L);
            producto.setCantidadEnStock(10);

        VentaDTO ventaDTO = new VentaDTO();
            ventaDTO.setIdentificadorCliente("12345");
            ventaDTO.setProducto(producto);
            ventaDTO.setCantidadComprada(5);

            // Simular repositorios
        Mockito.when(repoProducto.findById(1L)).thenReturn(Optional.of(producto));
        Mockito.when(repoVenta.save(Mockito.any(Venta.class))).thenAnswer(invocation -> {
            Venta venta = invocation.getArgument(0);
            venta.setId(100L); // Simular que se guardó correctamente
            return venta;
        });

            // Ejecutar método
        Venta ventaRegistrada = servicioVenta.register(ventaDTO);

            // Verificar resultados
        assertNotNull(ventaRegistrada);
        assertEquals(100L, ventaRegistrada.getId());
        assertEquals(5, ventaRegistrada.getCantidadComprada());
        assertEquals(5, producto.getCantidadEnStock()); // Stock reducido correctamente
    }

    @Test
    void testRegistrarVenta_SinStock() {
            // Simular datos
        Producto producto = new Producto();
            producto.setId(1L);
            producto.setCantidadEnStock(3);

        VentaDTO ventaDTO = new VentaDTO();
            ventaDTO.setProducto(producto);
            ventaDTO.setCantidadComprada(5); // Más de lo que hay en stock

            // Simular repositorio
        Mockito.when(repoProducto.findById(1L)).thenReturn(Optional.of(producto));

            // Ejecutar y verificar que lanza excepción
        assertThrows(VentaException.class, () -> servicioVenta.register(ventaDTO));
    }
}
