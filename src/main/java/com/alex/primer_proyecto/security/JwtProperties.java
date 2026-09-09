package com.alex.primer_proyecto.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration 
@Data 
/*  le decimos a spring que vincule automaticamente las propiedades del archivo prope..
que comiencen con el prefijo jwt */
@ConfigurationProperties (prefix = "jwt")

//solamente asignamos el valor que teniamos en propies a esas variables
public class JwtProperties {
    private  String secret;
    private Long expiration;
    
}
