package com.alex.primer_proyecto.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.primer_proyecto.entity.Autor;

// simplemete un intermediario que se comunica con la base de dattos
public interface AutorRepository extends JpaRepository<Autor, Long> {
    //creamos nuestros propios metodos
    Optional<Autor> findFirstByNombreIgnoreCase(String nombre);
    Optional<Autor> findFirstByEmail(String email );
    
}