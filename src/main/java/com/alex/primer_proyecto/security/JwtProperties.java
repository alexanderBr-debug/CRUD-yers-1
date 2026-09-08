package com.alex.primer_proyecto.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration 
@Data 
@ConfigurationProperties (prefix = "jwt")
public class JwtProperties {
    private  String secret;
    private Long expiration;
    
}
