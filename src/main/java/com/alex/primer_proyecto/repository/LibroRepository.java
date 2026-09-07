
package com.alex.primer_proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.primer_proyecto.entity.Libro;





public interface LibroRepository extends JpaRepository<Libro, Long>{

    Optional<Libro> findFirstByTituloIgnoreCase(String titulo);
   
}