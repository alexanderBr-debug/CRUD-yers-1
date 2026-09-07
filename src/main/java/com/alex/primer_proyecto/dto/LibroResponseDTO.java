package com.alex.primer_proyecto.dto;

import lombok.Data;

@Data 
public class LibroResponseDTO {
    private Long id;
    private String titulo;
    private boolean disponible;
    private Integer añoPublicacion;
    private String nombreAutor;
}
