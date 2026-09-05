package com.alex.primer_proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.primer_proyecto.entity.AutorEntity;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findFirstByNombreIgnoreCase(String nombre);
}