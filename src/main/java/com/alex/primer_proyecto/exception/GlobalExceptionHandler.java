package com.alex.primer_proyecto.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*aqui manejaremos las exceptiones sin esto cuando se lanze una exception siempre ocurrira un error 500
pero con podemos decir exactamente lo que esta pasando al usuario,comenzamos con la primera anotacion esta lo que hace 
es decirle a spring que esta clase va hacer un interceptor global de erros en pocas palabras cada vez que se lance
una exception el la interceptara y pondra el mensaje hhtp correcto */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*exceptionhandler es el encargado de de decirle a spring si en cualquier oarte del codigo se lanza esa
    exception ejecuta este metodo,el ex extrae el mensaje que habiamos puesto cuando colocamos es aexception  */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> manejarAutorNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<String> manejarAutorDuplicado(RecursoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler (LibroNoDisponibleException.class)
    public ResponseEntity<String> manejarLibroNoDisponible(LibroNoDisponibleException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    /*aqui tambien manejos las exceptiones lanzadas por el valited en los dtos,ccuando el json mandado por el cliente
    no cumple con las validaciones el servidor siempre lanzara esa exception tons los mismo de arriba si se lamza esa exception 
    ejecuta este metosd*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> manejarValidacion(MethodArgumentNotValidException ex) {
        //crea una lista "clave valor"para asociar cada campo imvalidp con su mensaje
     Map<String, String> errores = new HashMap<>();
     //recorremos la lista de los campos que no pasaron las validaciones
         ex.getBindingResult().getFieldErrors().forEach(error ->
        errores.put(error.getField(), error.getDefaultMessage())
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
}

    @ExceptionHandler(CredencialesInvalidasException.class)
        public ResponseEntity<String> manejarCredencialInvalidada(CredencialesInvalidasException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

        }

    }

