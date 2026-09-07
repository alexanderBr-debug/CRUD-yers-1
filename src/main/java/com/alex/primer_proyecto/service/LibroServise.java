package com.alex.primer_proyecto.service;


import com.alex.primer_proyecto.exception.RecursoDuplicadoException;
import com.alex.primer_proyecto.exception.RecursoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.alex.primer_proyecto.dto.LibroRequestDTO;
import com.alex.primer_proyecto.dto.LibroResponseDTO;
import com.alex.primer_proyecto.entity.Autor;
import com.alex.primer_proyecto.entity.Libro;
import com.alex.primer_proyecto.repository.AutorRepository;
import com.alex.primer_proyecto.repository.LibroRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service 
@AllArgsConstructor 
@Data 
public class LibroServise {


        private final AutorRepository autorRepository;
        private final LibroRepository libroRepository;
    // metodo para crear libros

        

    public LibroResponseDTO createLibro(LibroRequestDTO request) {


        //crear
    Autor autor = autorRepository.findById(request.getIdAutor())
        .orElseThrow(() -> new RecursoNoEncontradoException("El autor no existe en la base de datos"));

    libroRepository.findFirstByTituloIgnoreCase(request.getTitulo())
        .ifPresent(libro -> {
            throw new RecursoDuplicadoException("Libro duplicado en la base de datos");
        });

    Libro libro = new Libro();
    libro.setTitulo(request.getTitulo().trim());
    libro.setAñoPublicacion(request.getAñoPublicacion());
    libro.setDisponible(true);
    libro.setAutor(autor);
    libroRepository.save(libro);

    LibroResponseDTO response = new LibroResponseDTO();
    response.setId(libro.getId());
    response.setTitulo(libro.getTitulo());
    response.setDisponible(libro.isDisponible());
    response.setAñoPublicacion(libro.getAñoPublicacion());
    response.setNombreAutor(autor.getNombre());
    return response;
  }

        //ver todos
    public List<LibroResponseDTO> SeeAllLibros (LibroRequestDTO request){

        List<Libro> libro = libroRepository.findAll();
        List<LibroResponseDTO> response = new ArrayList<>();

        for (Libro libros : libro) {

            LibroResponseDTO responseDto = new LibroResponseDTO(); 
            responseDto.setId(libros.getId());
            responseDto.setTitulo(libros.getTitulo());
            responseDto.setAñoPublicacion(libros.getAñoPublicacion());
            responseDto.setDisponible(libros.isDisponible());
            responseDto.setNombreAutor(libros.getAutor().getNombre());
            response.add(responseDto);

            
        }

        return response;

    }

    //ver por id
    public LibroResponseDTO seeId(LibroRequestDTO request,Long id){
        Libro libro = libroRepository.findById(id)
        .orElseThrow(() -> new RecursoNoEncontradoException("libro no encontrado"));

        LibroResponseDTO response = new LibroResponseDTO();
        response.setId(libro.getId());
        response.setTitulo(libro.getTitulo());
        response.setDisponible(libro.isDisponible());
        response.setAñoPublicacion(libro.getAñoPublicacion());
        response.setNombreAutor(libro.getAutor().getNombre());
        return response;


    }

     //metodo para eliminar autor por id
        public String deleteLibro(Long id){
            Libro libro = libroRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("el libro no existe en la base de datos"));
            libroRepository.delete(libro);
            return "Libro eliminado correctamente";
        }

        //metodo para actualizar

        public LibroResponseDTO updateLibro(LibroRequestDTO request,Long id){

            Autor autor = autorRepository.findById(request.getIdAutor())
            .orElseThrow(() -> new RecursoNoEncontradoException("id del autor no encontrado"));
            
            Libro libro = libroRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("libro no encontrado"));

            libro.setTitulo(request.getTitulo());
            libro.setAñoPublicacion(request.getAñoPublicacion());
            libro.setDisponible(true);
            libro.setAutor(autor);
            libroRepository.save(libro);

            LibroResponseDTO response = new LibroResponseDTO();
            response.setId(libro.getId());
            response.setTitulo(libro.getTitulo());
            response.setDisponible(libro.isDisponible());
            response.setAñoPublicacion(libro.getAñoPublicacion());
            response.setNombreAutor(libro.getAutor().getNombre());
            return response;
      
               
        }

        

}