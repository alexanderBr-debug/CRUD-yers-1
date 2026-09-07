package  com.alex.primer_proyecto.dto;

import lombok.Data;

@Data 
public class LibroRequestDTO{
    
    private String titulo;
    private Integer añoPublicacion;
    private Long idAutor;

}