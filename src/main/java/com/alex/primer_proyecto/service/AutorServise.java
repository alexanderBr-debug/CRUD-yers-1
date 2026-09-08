package com.alex.primer_proyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.alex.primer_proyecto.dto.AutorRequestDTO;
import com.alex.primer_proyecto.dto.AutorResponseDTO;
import com.alex.primer_proyecto.dto.LibroResponseDTO;
import com.alex.primer_proyecto.entity.Autor;
import com.alex.primer_proyecto.entity.Libro;
import com.alex.primer_proyecto.exception.RecursoDuplicadoException;
import com.alex.primer_proyecto.exception.RecursoNoEncontradoException;
import com.alex.primer_proyecto.repository.AutorRepository;
import com.alex.primer_proyecto.repository.LibroRepository;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor

@Data
@Service
public class AutorServise {

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;
    private final PasswordEncoder passwordEncoder;

    // METODO PARA CREAR USUARIOS
    public AutorResponseDTO createAutor(AutorRequestDTO request){
        autorRepository.findFirstByNombreIgnoreCase(request.getNombre())
        .ifPresent(autor -> {
            throw new RecursoDuplicadoException("el autor existe en la base de datos");
        });

        //sino no esta presente,lo crearemos
        Autor autor = new Autor();
        autor.setNombre(request.getNombre().trim());
        autor.setNacionalidad(request.getNacionalidad().trim());
        autorRepository.save(autor);

        AutorResponseDTO response = new AutorResponseDTO();
        response.setNombre(autor.getNombre());
        response.setNacionalidad(autor.getNacionalidad());
        response.setId(autor.getId());
        return response;
    }

    // METODO PARA BUSCAR AUTOR POR NOMBRE
   
    public AutorResponseDTO SeeAutor( String nombre ){

        Autor autor = autorRepository.findFirstByNombreIgnoreCase(nombre)
        .orElseThrow(() -> new RecursoNoEncontradoException("el autor no existe en la base de datos"));
           
            AutorResponseDTO response = new AutorResponseDTO();
            response.setNombre(autor.getNombre());
            response.setNacionalidad(autor.getNacionalidad());
            response.setId(autor.getId());
            return response;
        };

        //metodo para mostrar todos 
        public List<AutorResponseDTO> SeeAllAutores(AutorRequestDTO request){

            List<Autor> autor = autorRepository.findAll();
            List<AutorResponseDTO> response = new ArrayList<>();
            for(Autor autores : autor){
                AutorResponseDTO dto = new AutorResponseDTO();
                dto.setNombre(autores.getNombre());
                dto.setNacionalidad(autores.getNacionalidad());
                dto.setId(autores.getId());
                response.add(dto);


            }
            return response;
            

            
        }

        //metodo para eliminar autor por nombre
        public String deleteAutor(String nombre){
            Autor autor = autorRepository.findFirstByNombreIgnoreCase(nombre)
            .orElseThrow(() -> new RecursoNoEncontradoException("el autor no existe en la base de datos"));
            autorRepository.delete(autor);
            return "Autor eliminado correctamente";
        }

        //metodo para actualizar

        public AutorResponseDTO updateAutor(AutorRequestDTO request,Long id){

           Autor autor = autorRepository.findById(id)
            .orElseThrow(() -> 
                new RecursoNoEncontradoException("e autor no exixte en la base de datos"));

                
                autor.setNombre(request.getNombre().trim());
                autor.setNacionalidad(request.getNacionalidad().trim());
                autorRepository.save(autor);

                AutorResponseDTO response = new AutorResponseDTO();
                response.setId(autor.getId());
                response.setNombre(autor.getNombre());
                response.setNacionalidad(autor.getNacionalidad());
                return  response;
            
        }

        public List<LibroResponseDTO> allLibros(Long id){

            Autor autor = autorRepository.findById(id)
            .orElseThrow(()  -> new RecursoNoEncontradoException("autor no existente"));
            List<LibroResponseDTO> response = new ArrayList<>();

            for (Libro libro :  autor.getLibros()) {
                LibroResponseDTO dto = new LibroResponseDTO();

                dto.setId(libro.getId());
                dto.setTitulo(libro.getTitulo());
                dto.setDisponible(libro.isDisponible());
                dto.setAñoPublicacion(libro.getAñoPublicacion());
                dto.setNombreAutor(autor.getNombre());
                response.add(dto);               
            }
            return  response;
        }

        

        
    }

