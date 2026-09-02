package com.alex.primer_proyecto.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data//para no poner get y set
@NoArgsConstructor//para crear constructor vacio
@AllArgsConstructor //crea constructor 
@Entity//para decirle que es una entidad
@Table(name = "usuarios")//para decirle el nombre de la tabla
public class UserEntity{

    @Id//para decirle que es la llave primaria
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)//para decirle que es autoincrementable
    @Column(name = "id_usuario")//para decirle el nombre de la columna
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "direccion")
    private String direccion;

}

