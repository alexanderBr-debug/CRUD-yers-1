package com.alex.primer_proyecto.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//esto es lo primero de todo el proyecto
/*ahora haremos los filtros y verificaciones de tokens,lo primero que haremos es decirle a 
spring que esta clase va hacer un component que lo que hace es registrar esta clase en un contendor de spring para
poderla inyectarla dentro securityFiltre o otra. */
@Component 
@RequiredArgsConstructor 

/*  once... es una clase adstracta proporcionada por spring esta misma garantiza que un filtro
de servlet se ejecute solo una vez por peticion dentro del mismo hilo de solicitud es necesario porque algunos filtros 
pueden invocarse multiples veces para una sola peticion hhtp en varios escenarios en resumen evita ejecutaciones duplicadas
tanta cosa sola para eso,aunque hace mas cosas pero que peresa escribir */
public class JwtAuthenticationFiltre extends OncePerRequestFilter {

    private final JwtService jwtService;

    //interfaz de spring usadas para cargar los detalles del usuario desde la base de datos a partir del email
    private final UserDetailsService userDetailsService;

    @Override 
    protected void  doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull  HttpServletResponse response,
                                    @NonNull FilterChain filterChain
     ) throws ServletException,IOException{

        // 1. Extraer el encabezado Authorization de la petición HTTP
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    // 2. Si no hay encabezado o no empieza con "Bearer ", ignorar y continuar la cadena de filtros
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    // 3. Extraer el token (quitando el prefijo "Bearer ")
    jwt = authHeader.substring(7);
    userEmail = jwtService.extraerEmail(jwt);

    // 4. Si hay email y el usuario no está aún autenticado en el contexto de Spring Security
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

        // 5. Si el token es válido, registrar al usuario en el SecurityContextHolder
        if (jwtService.esTokenValido(jwt, userDetails.getUsername())) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            // Establecer la autenticación globalmente para la petición actual
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    // 6. Pasar al siguiente filtro de la cadena
    filterChain.doFilter(request, response);

     }

    

    
}
