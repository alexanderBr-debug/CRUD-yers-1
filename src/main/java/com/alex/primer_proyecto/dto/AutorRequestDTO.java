package com.alex.primer_proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// para no poner set y get
@Data 
public class AutorRequestDTO{

    /* esos son los requesitos por lo que debera cumplir el json si no cumple con esos
    mandara una exception es muy util ya que asi no congestionamos tanto el sistema con peticiones
    inecesarias */


    //dice que no puede ser nulo ni vacio
    @NotBlank (message = "el nombre no puede estar vacio")
    //ponemos un rango
    @Size (min = 2, max = 50,message = "el nombre debe star en ese rango")
    private String nombre;


    @NotBlank (message = "no puede estar vacia")
    private String nacionalidad;

    
}