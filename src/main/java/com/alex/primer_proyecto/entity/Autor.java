
package com.alex.primer_proyecto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    private String passWord;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "nacionalidad")
    private String nacionalidad;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    
}