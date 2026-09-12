package com.alex.primer_proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.UserLoginRequestDTO;
import com.alex.primer_proyecto.dto.UserLoginResponseDTO;

import com.alex.primer_proyecto.service.UserService;

import lombok.RequiredArgsConstructor;



 @RestController 
@RequiredArgsConstructor 
@RequestMapping ("/auth")
public class authController {

   //instanciamos las objetos requeridos
   

      private final UserService userService;

    

    //ruta o url
    @PostMapping ("/registre")
    public ResponseEntity<UserLoginResponseDTO> registre( @Validated @RequestBody UserLoginRequestDTO request){

        UserLoginResponseDTO response = userService.registra(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
   }

   @PostMapping ("/login")
    public ResponseEntity<UserLoginResponseDTO> login( @Validated @RequestBody UserLoginRequestDTO request){

        UserLoginResponseDTO response = userService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
            
   }



   

}

            


