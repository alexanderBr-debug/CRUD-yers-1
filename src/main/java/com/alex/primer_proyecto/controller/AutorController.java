package com.alex.primer_proyecto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.AutorRequestDTO;
import com.alex.primer_proyecto.dto.AutorResponseDTO;
import com.alex.primer_proyecto.service.AutorServise;

import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor 
@RequestMapping ("/autores")
public class AutorController {

    private final AutorServise autorServise;

    // METODO PARA CREAR AUTOR
    @PostMapping ("/create")
    public ResponseEntity<AutorResponseDTO> createAutor(@RequestBody AutorRequestDTO request){
        AutorResponseDTO response =autorServise.createAutor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // METODO PARA BUSCAR AUTOR POR NOMBRE
     @GetMapping ("/SeeAutor/{nombre}")
     public ResponseEntity<AutorResponseDTO> SeeAutor(@PathVariable String nombre){
        AutorResponseDTO response = autorServise.SeeAutor(nombre);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     // METODO PARA MOSTRAR TODOS LOS AUTORES
     @GetMapping ("/seeAllAutores")
     public ResponseEntity<List<AutorResponseDTO>> SeeAllAutores(@RequestBody AutorRequestDTO request){
        List<AutorResponseDTO> response = autorServise.SeeAllAutores(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     // METODO PARA ELIMINAR AUTOR POR NOMBRE
     @DeleteMapping ("/deleteAutor/{nombre}")
     public ResponseEntity<String> deleteAutor(@PathVariable  String nombre){
        String response = autorServise.deleteAutor(nombre);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
     }

}