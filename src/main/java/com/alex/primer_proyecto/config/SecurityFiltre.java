package com.alex.primer_proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityFiltre {

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            //desactivamos una proteccion de sitios wed
            .csrf(csrf -> csrf.disable())
            // decimo que sesta ruta es segura y no necesita login
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login/hola").permitAll()
                //las demas si necesitan login
                .anyRequest().authenticated()
            );
        return http.build();
    }
    
}
