package com.alex.primer_proyecto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.LibroRequestDTO;
import com.alex.primer_proyecto.dto.LibroResponseDTO;
import com.alex.primer_proyecto.service.LibroServise;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController 
@Data 
@AllArgsConstructor 
@RequestMapping ("/LibroController")
public class LibroController {

        private final LibroServise libroServise;

    @PostMapping ("/create")
    public ResponseEntity<LibroResponseDTO> createLibro(@RequestBody LibroRequestDTO request){
        LibroResponseDTO response = libroServise.createLibro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping ("/seeAllLibros")
    public ResponseEntity<List<LibroResponseDTO>> seeAllLibros( LibroRequestDTO request){
        List<LibroResponseDTO> response = libroServise.SeeAllLibros(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("/seeLibro/{id}")
    public ResponseEntity<LibroResponseDTO> seeId( LibroRequestDTO request,@PathVariable long id){
        LibroResponseDTO response = libroServise.seeId(request,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // METODO PARA ELIMINAR AUTOR POR ID
     @DeleteMapping ("/deleteLibro/{id}")
     public ResponseEntity<String> deleteLibro(@PathVariable  Long id){
        String response = libroServise.deleteLibro(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
     }

     //update
     @PutMapping ("updateLibro/{id}")
     public ResponseEntity<LibroResponseDTO> updateLibro(@RequestBody LibroRequestDTO request,@PathVariable Long id){
        LibroResponseDTO response = libroServise.updateLibro(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

     }
}