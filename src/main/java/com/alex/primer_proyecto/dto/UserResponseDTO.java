package com.alex.primer_proyecto.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private String direccion;
}
