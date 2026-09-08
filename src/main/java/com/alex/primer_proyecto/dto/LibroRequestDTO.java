package  com.alex.primer_proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class LibroRequestDTO{
    
    @NotBlank (message = "no puede estar vacio")
    @Size (min = 2, max = 50,message = "debe estar estar dentro del rango")
    private String titulo;
    
    private Integer añoPublicacion;
    private Long idAutor;

}