package com.alex.primer_proyecto.service;

import com.alex.primer_proyecto.repository.UserRespository;
import com.alex.primer_proyecto.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alex.primer_proyecto.dto.UserLoginRequestDTO;
import com.alex.primer_proyecto.dto.UserLoginResponseDTO;
import com.alex.primer_proyecto.entity.User;
import com.alex.primer_proyecto.exception.CredencialesInvalidasException;
import com.alex.primer_proyecto.exception.RecursoDuplicadoException;
import com.alex.primer_proyecto.exception.RecursoNoEncontradoException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Data 


public class UserService {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    

    public UserLoginResponseDTO registra (UserLoginRequestDTO request){

        userRespository.findFirstByEmail(request.getEmail())

        .ifPresent((user) -> {
            throw new RecursoDuplicadoException("el usuario ya existe en la base de dstos");
        });

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassWord()));
        userRespository.save(user);


         String token = jwtService.generarToken(user.getEmail());

         UserLoginResponseDTO response = new UserLoginResponseDTO();
         response.setToken(token);

         return response;
    }

    public UserLoginResponseDTO login(UserLoginRequestDTO request){

       User user = userRespository.findFirstByEmail(request.getEmail())
       .orElseThrow(() -> new RecursoNoEncontradoException("el usurio mo esta registrado"));

       if(!passwordEncoder.matches(request.getPassWord(),user.getPassword())){
        throw new CredencialesInvalidasException("contraseña incorecta");
       }
       String token = jwtService.generarToken(request.getEmail());

       UserLoginResponseDTO response = new UserLoginResponseDTO();
       response.setToken(token);
       return response;

    }
    
}
