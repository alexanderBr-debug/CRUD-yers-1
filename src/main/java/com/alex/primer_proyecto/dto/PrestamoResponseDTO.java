package com.alex.primer_proyecto.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data 
public class PrestamoResponseDTO {

    private Long id;
    private String nombreUsuario;
    private LocalDateTime fechaPrestamo;
    private String tituloLibro;     
    private String nombreAutor;
}
