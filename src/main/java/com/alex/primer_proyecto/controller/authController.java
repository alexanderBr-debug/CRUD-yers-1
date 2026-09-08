package com.alex.primer_proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.LoginRequestDTO;
import com.alex.primer_proyecto.dto.LoginResponseDTO;
import com.alex.primer_proyecto.entity.Autor;
import com.alex.primer_proyecto.exception.CredencialesInvalidasException;
import com.alex.primer_proyecto.exception.RecursoNoEncontradoException;
import com.alex.primer_proyecto.repository.AutorRepository;
import com.alex.primer_proyecto.security.JwtService;

import lombok.AllArgsConstructor;



@RestController 
@AllArgsConstructor 
@RequestMapping ("/login")
public class authController {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private  final AutorRepository autorRepository;

    @PostMapping ("/hola")
    public ResponseEntity<LoginResponseDTO> login( @Validated @RequestBody LoginRequestDTO request){
    Autor autor =  autorRepository.findFirstByEmail(request.getEmail())
         .orElseThrow(() -> new RecursoNoEncontradoException("usuario no encontrado"));

         if(!passwordEncoder.matches(request.getPassWord(), autor.getPassWord())){
            throw new CredencialesInvalidasException("contraseña incorrecta");
         }

         String token = jwtService.generarToken(autor.getEmail());

         LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
            
}
}
