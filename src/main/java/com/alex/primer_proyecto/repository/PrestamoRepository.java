package com.alex.primer_proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alex.primer_proyecto.entity.Prestamo;



public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}