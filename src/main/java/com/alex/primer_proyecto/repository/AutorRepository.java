package com.alex.primer_proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.primer_proyecto.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findFirstByNombreIgnoreCase(String nombre);
}