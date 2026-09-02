package com.alex.primer_proyecto.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.UserRequestDTO;
import com.alex.primer_proyecto.dto.UserResponseDTO;
import com.alex.primer_proyecto.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;









@RestController
@RequiredArgsConstructor
@RequestMapping("/primer-proyecto")
public class UsersController {

    private final UserService userService;

    //metodo para crear usarios de tipo post 

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        UserResponseDTO response = userService.createUSer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    
    }

    //metodo para ver usuarios de tipo get
    @GetMapping("/see")
    public ResponseEntity<List<UserResponseDTO>> seeAllUsers(){
        List<UserResponseDTO> response = userService.seeAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //metodo para ver un usuario por su ID
    @GetMapping("/see/{id}")
    public ResponseEntity<UserResponseDTO> seeUserByid(@PathVariable Long id) {
        UserResponseDTO response = userService.seeUserByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //metodo para eliminar un usuario por su ID
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        String response = userService.deleteUserByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
     }

     @PutMapping("/put/{id}")
        
        public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Long id, @RequestBody UserRequestDTO request) {
            UserResponseDTO response = userService.updateUserById(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
     
     
     }
    }


    
