package com.alex.primer_proyecto.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor 
public class JwtService {

    private final JwtProperties jwtProperties;
    public String generarToken(String email) {
        //convierto mi clave a un objeto tipo key
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

        //builer empieza a construir el token pieza por pieza
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
            .signWith(key, SignatureAlgorithm.HS256)
            //arma el string final
            .compact();
    }
}