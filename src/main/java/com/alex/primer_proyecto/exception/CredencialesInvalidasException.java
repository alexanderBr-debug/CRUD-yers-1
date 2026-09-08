package com.alex.primer_proyecto.exception;

public class CredencialesInvalidasException extends  RuntimeException{

    public CredencialesInvalidasException(String mensaje ){
        super(mensaje);
    }
    
}
