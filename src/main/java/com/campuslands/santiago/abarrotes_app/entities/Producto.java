package com.campuslands.santiago.abarrotes_app.entities;

import com.campuslands.santiago.abarrotes_app.entities.utils.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Producto {
        //Atributos de Producto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String sku;
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "cantidad_stock")
    private Integer cantidadEnStock;
    @Enumerated(EnumType.STRING)
    private Categoria nombreCategoria;

    //Constructores de Producto
    //Asignadores de atributos de Producto (setters)
    //Lectores de atributos de Producto (getters)
    //Métodos de Producto
    
}
