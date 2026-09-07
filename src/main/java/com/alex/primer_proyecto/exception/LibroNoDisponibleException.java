package com.alex.primer_proyecto.exception;

public class LibroNoDisponibleException extends RuntimeException {

     public LibroNoDisponibleException (String mensaje){
        super(mensaje);
    }
    
}
