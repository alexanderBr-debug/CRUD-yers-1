package com.alex.primer_proyecto.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestamo{

  
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column (name = "id")
   private Long id;

   @Column (name = "nombreUsuario")
   private String nombreUsuario;

   @Column (name = "fechaPrestamo")
   private LocalDateTime fechaPrestamo;

   @ManyToOne 
   @JoinColumn (name = "libro-id")
   private Libro libroEntity;

   //creamos un metod para obtener la hora dia segundos etc
   @PrePersist
    protected void onCreate() {
        this.fechaPrestamo = LocalDateTime.now();
    }

}