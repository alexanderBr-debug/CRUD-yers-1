
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

    //decimos que es de tipo id Y autoicrementable con geretion tipe
    /*column sirve para poner los nombres de las columnas en la base de datos ya que sino esta misma pondra 
    nombres genericos bien feos*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    private String passWord;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "nacionalidad")
    private String nacionalidad;

    @Column (name = ("email"))
    private String email;

    /*una relacion simple mappe le dice a spring "auto es la llave foranea de esta relacion" esto evita
    duplicar llaves */
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    
}