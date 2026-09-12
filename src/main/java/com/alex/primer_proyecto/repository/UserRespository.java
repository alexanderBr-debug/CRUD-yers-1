package com.alex.primer_proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.primer_proyecto.entity.User;

public interface UserRespository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);
    
}
