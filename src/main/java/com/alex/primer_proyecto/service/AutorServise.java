package com.alex.primer_proyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.alex.primer_proyecto.dto.AutorRequestDTO;
import com.alex.primer_proyecto.dto.AutorResponseDTO;
import com.alex.primer_proyecto.entity.AutorEntity;
import com.alex.primer_proyecto.exception.AutorDuplicadoException;
import com.alex.primer_proyecto.exception.AutorNoEncontradoException;
import com.alex.primer_proyecto.repository.AutorRepository;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor

@Data
@Service
public class AutorServise {

    private final AutorRepository autorRepository;

    // METODO PARA CREAR USUARIOS
    public AutorResponseDTO createAutor(AutorRequestDTO request){
        autorRepository.findFirstByNombreIgnoreCase(request.getNombre())
        .ifPresent(autor -> {
            throw new AutorDuplicadoException("el autor existe en la base de datos");
        });

        //sino no esta presente,lo crearemos
        AutorEntity autor = new AutorEntity();
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

        AutorEntity autor = autorRepository.findFirstByNombreIgnoreCase(nombre)
        .orElseThrow(() -> new AutorNoEncontradoException("el autor no existe en la base de datos"));


            
            AutorResponseDTO response = new AutorResponseDTO();
            response.setNombre(autor.getNombre());
            response.setNacionalidad(autor.getNacionalidad());
            return response;


        };

        //metodo para mostrar todos 
        public List<AutorResponseDTO> SeeAllAutores(AutorRequestDTO request){

            List<AutorEntity> autor = autorRepository.findAll();
            List<AutorResponseDTO> response = new ArrayList<>();
            for(AutorEntity autores : autor){
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
            AutorEntity autor = autorRepository.findFirstByNombreIgnoreCase(nombre)
            .orElseThrow(() -> new AutorNoEncontradoException("el autor no existe en la base de datos"));
            autorRepository.delete(autor);
            return "Autor eliminado correctamente";
        }

        

        
    }

