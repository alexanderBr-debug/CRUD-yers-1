package com.alex.primer_proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class LoginRequestDTO {

    @NotBlank (message = ("no puede ser nulo"))
    @Size (min = 2,max = 20,message = ("debe estar dentro del rango"))
    private  String passWord;

    @Email (message = ("formato email"))
    private String email;
}
