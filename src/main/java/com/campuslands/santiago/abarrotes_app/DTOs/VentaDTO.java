package com.campuslands.santiago.abarrotes_app.DTOs;

import com.campuslands.santiago.abarrotes_app.entities.Producto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VentaDTO {
        //Atributos de VentaDTO
    private Long id;
    private String identificadorCliente;
    private Producto producto;
    private Integer cantidadComprada;
    private Double montoTotalVenta;
    private LocalDateTime fechaVenta;

    //Constructores de VentaDTO
    //Asignadores de atributos de VentaDTO (setters)
    //Lectores de atributos de VentaDTO (getters)
    //Métodos de VentaDTO
    
}
