package com.alex.primer_proyecto.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.primer_proyecto.dto.PrestamoRequestDTO;
import com.alex.primer_proyecto.dto.PrestamoResponseDTO;
import com.alex.primer_proyecto.entity.Libro;
import com.alex.primer_proyecto.entity.Prestamo;
import com.alex.primer_proyecto.exception.LibroNoDisponibleException;
import com.alex.primer_proyecto.exception.RecursoNoEncontradoException;
import com.alex.primer_proyecto.repository.LibroRepository;
import com.alex.primer_proyecto.repository.PrestamoRepository;

import lombok.RequiredArgsConstructor;



@Service 
@RequiredArgsConstructor

public class PrestamoServise{

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;


    @Transactional 
    public PrestamoResponseDTO createPrestamo(PrestamoRequestDTO request){

        
        Libro libro = libroRepository.findById(request.getIdLibro())
        .orElseThrow(() -> new RecursoNoEncontradoException("libro no exixte en la base de datos"));

        if(!libro.isDisponible()){
            throw new LibroNoDisponibleException("libro no esta disponible");
        }
        
             Prestamo prestamo = new Prestamo();           
            prestamo.setNombreUsuario(request.getNombreUsuario());
            prestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
            libro.setDisponible(false);
            prestamo.setLibroEntity(libro);
            libroRepository.save(libro);
            prestamoRepository.save(prestamo);
            
            PrestamoResponseDTO response = new PrestamoResponseDTO();
            response.setId(prestamo.getId());
            response.setNombreUsuario(prestamo.getNombreUsuario());
            response.setTituloLibro(libro.getTitulo());
            response.setNombreAutor(libro.getAutor().getNombre());
            response.setFechaPrestamo(prestamo.getFechaPrestamo());
            return  response;
    }

    public PrestamoResponseDTO update(PrestamoRequestDTO request,Long id ){

        Libro libro = libroRepository.findById(request.getIdLibro())
        .orElseThrow(() -> new RecursoNoEncontradoException("el libro no se encuentra en la base de datos"));
        
        Prestamo prestamo = prestamoRepository.findById(id)
        .orElseThrow(() -> new RecursoNoEncontradoException("libro no encontrado"));

        prestamo.setNombreUsuario(request.getNombreUsuario());
        prestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
        libro.setDisponible(true);
        prestamo.setLibroEntity(libro);
        libroRepository.save(libro);
        prestamoRepository.save(prestamo);

        PrestamoResponseDTO response = new PrestamoResponseDTO();
            response.setId(prestamo.getId());
            response.setNombreUsuario(prestamo.getNombreUsuario());
            response.setTituloLibro(libro.getTitulo());
            response.setNombreAutor(libro.getAutor().getNombre());
            response.setFechaPrestamo(prestamo.getFechaPrestamo());
            return  response;    
    }

}

