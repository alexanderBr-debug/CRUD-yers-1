package com.alex.primer_proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.primer_proyecto.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
Optional<UserEntity> findFirstByEmail(String email);


    
}