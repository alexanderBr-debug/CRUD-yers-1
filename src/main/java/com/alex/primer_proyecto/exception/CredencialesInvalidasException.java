package com.alex.primer_proyecto.exception;

/*aqui crearemos nuestras exceptions peronalizadas que es una exception? es como un evento inesperado
pero puede causar grandes problemas sin estas cuando ocurre ese evento el sistema se cae pero con exception
podremos decir si pasa esto haz esto y asi y la extendemos de runtime lo que es lanzar esa exception en tiempo de ejecucion */
public class CredencialesInvalidasException extends  RuntimeException{

    public CredencialesInvalidasException(String mensaje ){
        super(mensaje);
    }
    
}
