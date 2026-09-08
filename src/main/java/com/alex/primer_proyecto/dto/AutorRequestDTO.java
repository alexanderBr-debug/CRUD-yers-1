package com.alex.primer_proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class AutorRequestDTO{

    //dice que no puede ser nulo ni vacio
    @NotBlank (message = "el nombre no puede estar vacio")
    //ponemos un rango
    @Size (min = 2, max = 50,message = "el nombre debe star en ese rango")
    private String nombre;

    @NotBlank (message = ("no puede ser nulo"))
    @Size (min = 2,max = 20,message = ("debe estar dentro del rango"))
    private  String passWord;

    @NotBlank (message = "no puede estar vacia")
    private String nacionalidad;
}