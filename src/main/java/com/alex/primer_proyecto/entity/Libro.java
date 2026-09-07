package com.alex.primer_proyecto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "titulo")
    private String titulo;

    @Column (name = "añoPublicacion")
    private Integer añoPublicacion;

    @Column (name = "disponible")
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}