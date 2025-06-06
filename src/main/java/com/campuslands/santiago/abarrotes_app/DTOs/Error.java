package com.campuslands.santiago.abarrotes_app.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Error {
        //Atributos de Error
    private LocalDateTime fecha;
    private int httpStatus;
    private String mensaje;

    //Constructores de Error
    //Asignadores de atributos de Error (setters)
    //Lectores de atributos de Error (getters)
    //Métodos de Error
}
