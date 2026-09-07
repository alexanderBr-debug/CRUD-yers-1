package com.alex.primer_proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.PrestamoRequestDTO;
import com.alex.primer_proyecto.dto.PrestamoResponseDTO;
import com.alex.primer_proyecto.service.PrestamoServise;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController 
@AllArgsConstructor 
@Data 
@RequestMapping ("prestamo")
public class PrestamoController{

    private final PrestamoServise prestamoServise;

    @PostMapping ("/create")
    public ResponseEntity<PrestamoResponseDTO> create(@RequestBody PrestamoRequestDTO request){
        PrestamoResponseDTO response = prestamoServise.createPrestamo(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}