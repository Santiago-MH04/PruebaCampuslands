package com.campuslands.santiago.abarrotes_app.controllers;

import com.campuslands.santiago.abarrotes_app.DTOs.Error;
import com.campuslands.santiago.abarrotes_app.exceptions.VentaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandlingController {
    //Atributos de ErrorHandlingController
    //Constructores de ErrorHandlingController
    //Asignadores de atributos de ErrorHandlingController (setters)
    //Lectores de atributos de ErrorHandlingController (getters)
        //Métodos de ErrorHandlingController
    @ExceptionHandler({NoSuchElementException.class, ResponseStatusException.class})
    public ResponseEntity<Error> notFoundException(Exception e){
        Error error = new Error();  //Así como se envía un DTO, se puede enviar otro objeto serializable, como un Map
            error.setNombre("Error de búsqueda de producto");
            error.setMensaje(e.getMessage());
            error.setHttpStatus(HttpStatus.NOT_FOUND.value());
            error.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Error> categoriaMismatchException(Exception e){
        Error error = new Error();  //Así como se envía un DTO, se puede enviar otro objeto serializable, como un Map
            error.setNombre("Error de relación de categorías");
            error.setMensaje("Categoría no encontrada");
            error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            error.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({VentaException.class})
    public ResponseEntity<Error> ventaRegistrationException(Exception e){
        Error error = new Error();  //Así como se envía un DTO, se puede enviar otro objeto serializable, como un Map
            error.setNombre("Error registrando la venta");
            error.setMensaje(e.getMessage());
            error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            error.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
