package com.alex.primer_proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.primer_proyecto.dto.PrestamoRequestDTO;
import com.alex.primer_proyecto.dto.PrestamoResponseDTO;
import com.alex.primer_proyecto.service.PrestamoServise;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController 
@Data 
@RequiredArgsConstructor 
@RequestMapping ("prestamo")
public class PrestamoController{

    private final PrestamoServise prestamoServise;

    @PostMapping ("/create")
    public ResponseEntity<PrestamoResponseDTO> create(@RequestBody PrestamoRequestDTO request){
        PrestamoResponseDTO response = prestamoServise.createPrestamo(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<PrestamoResponseDTO> update(@RequestBody PrestamoRequestDTO request,@PathVariable Long id){
        PrestamoResponseDTO response = prestamoServise.update(request,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}