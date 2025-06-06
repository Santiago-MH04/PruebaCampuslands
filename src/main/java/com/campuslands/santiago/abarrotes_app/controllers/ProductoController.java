package com.campuslands.santiago.abarrotes_app.controllers;

import com.campuslands.santiago.abarrotes_app.DTOs.ProductoDTO;
import com.campuslands.santiago.abarrotes_app.entities.Producto;
import com.campuslands.santiago.abarrotes_app.entities.utils.Categoria;
import com.campuslands.santiago.abarrotes_app.services.abstractions.ServicioProducto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoController {
        //Atributos de ProductoController
    private final ServicioProducto servicioProducto;

        //Constructores de ProductoController
    public ProductoController(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    //Asignadores de atributos de ProductoController (setters)
    //Lectores de atributos de ProductoController (getters)
        //Métodos de ProductoController
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Producto> findAll(){
        return this.servicioProducto.findAll();
    }

     @GetMapping("/filtrar")
     @ResponseStatus(value = HttpStatus.OK)
     public List<Producto> findByCategoria(@RequestParam(name = "categoria", defaultValue = "OTROS") Categoria categoria){
            // Por defecto, se le asigna la categoría OTROS
        return this.servicioProducto.findByCategoria(categoria);
     }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.servicioProducto.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Producto> createProduct(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(this.servicioProducto.register(productoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return this.servicioProducto.findById(id)
            .map(p -> ResponseEntity.ok(this.servicioProducto.register(productoDTO))) // Si existe, actualiza
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")); // Si no existe, lanza error 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        return this.servicioProducto.findById(id)
            .map(p -> ResponseEntity.ok("Producto eliminado con éxito")) // Si existe, elimina
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")); // Si no existe, lanza error 404;
    }
}
