package com.alex.primer_proyecto.security;

import io.jsonwebtoken.Claims;
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

    //sacamos el email que habiamos guardado en el subject para poder identificar el usuario
    public String extraerEmail(String token){
        return extraerClaims(token).getSubject();
    }

    //hacemos validaciones para ver si ya expiro el token po si la fira es correcta
    public boolean esTokenValido(String token) {
    try {
        Claims claims = extraerClaims(token);
        return !claims.getExpiration().before(new Date());
    } catch (Exception e) {
        return false;
    }
    }

    //abre el token y revisa la firma,si esta todo correcto saca el claims
    private Claims extraerClaims(String token) {
    Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
    }
}