package com.campuslands.santiago.abarrotes_app.DTOs;

import com.campuslands.santiago.abarrotes_app.entities.utils.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductoDTO {
        //Atributos de ProductoDTO
    private Long id;
    private String nombre;
    private String sku;
    private Double precioUnitario;
    private Integer cantidadEnStock;
    private Categoria nombreCategoria;

    //Constructores de ProductoDTO
    //Asignadores de atributos de ProductoDTO (setters)
    //Lectores de atributos de ProductoDTO (getters)
    //Métodos de ProductoDTO
    
}
