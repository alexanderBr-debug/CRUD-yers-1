package com.alex.primer_proyecto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.AutorRequestDTO;
import com.alex.primer_proyecto.dto.AutorResponseDTO;
import com.alex.primer_proyecto.dto.LibroResponseDTO;
import com.alex.primer_proyecto.service.AutorServise;

import lombok.RequiredArgsConstructor;




//creamos un controller para autores
@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/autores")
public class AutorController {

   /*  instanciamos autorservise ya que necesitarmos los metodos que el tiene ya que el controler solo 
   recibe y manda */
    private final AutorServise autorServise;
    

    // METODO PARA CREAR AUTOR
    @PostMapping ("/create")

    /*aqui resivimos la peticion del cliente,antes de convertir el json a un objeto(normamente dto) hacemos
    unas validaciones con valited el revisa el json llegado y este mismo debera cumplir con unos requisitos 
    impuestos por nosotros si los cumple ay si los convertira a objeto con requestbody */
    public ResponseEntity<AutorResponseDTO> createAutor(@Validated  @RequestBody AutorRequestDTO request){

      //le pasamos ese dto al servise y el nos respondera con response y simplemente retornamos 
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

     //metodo para actualizar

     @PutMapping ("/update/{id}")
     public ResponseEntity<AutorResponseDTO> updateAutor (@RequestBody AutorRequestDTO request,@PathVariable Long id){
      AutorResponseDTO response = autorServise.updateAutor(request,id);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     @GetMapping ("/{id}/libros")
     public ResponseEntity<List<LibroResponseDTO>> allLibros(@PathVariable Long id){
      List<LibroResponseDTO> response = autorServise.allLibros(id);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
      
     }
   }
     