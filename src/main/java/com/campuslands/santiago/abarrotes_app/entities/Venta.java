package com.campuslands.santiago.abarrotes_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Venta {
        //Atributos de Venta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cc_cliente")
    private String identificadorCliente;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto")
    private Producto producto;
    private Integer cantidadComprada;
    @Column(name = "total_venta")
    private Double montoTotalVenta;
    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    //Constructores de Venta
    //Asignadores de atributos de Venta (setters)
    //Lectores de atributos de Venta (getters)
        //Métodos de Venta
    @PrePersist
    public void PrePersist(){
        this.montoTotalVenta = this.cantidadComprada * this.producto.getPrecioUnitario();
        this.fechaVenta= LocalDateTime.now();
    }
}
