package com.alex.primer_proyecto.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 1. Le dice a Spring: "Esta clase manejará los errores de toda la API"
public class GlobalExceptionHandler {

    // 2. Le dice a Spring: "Cuando salte un RecursoNoEncontradoException, ejecuta este método"
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        
        // Creamos la respuesta JSON personalizada que verá el usuario
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("mensaje", ex.getMessage()); // "Usuario no encontrado con id: 5"
        respuesta.put("codigo", HttpStatus.NOT_FOUND.value()); // 404

        // Retornamos el JSON con el status HTTP 404 NOT FOUND
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    
}