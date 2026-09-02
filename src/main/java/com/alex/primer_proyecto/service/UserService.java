package com.alex.primer_proyecto.service;




import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.alex.primer_proyecto.dto.UserRequestDTO;
import com.alex.primer_proyecto.dto.UserResponseDTO;
import com.alex.primer_proyecto.entity.UserEntity;
import com.alex.primer_proyecto.exception.RecursoDuplicadoexception;
import com.alex.primer_proyecto.exception.RecursoNoEncontradoException;
import com.alex.primer_proyecto.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository  userRepository;
    
    /** metodo para crear usuarios 

    @Param request
    @return
*/ 
public UserResponseDTO createUSer(UserRequestDTO  request) {
    UserEntity userCreate = userRepository.findFirstByEmail(request.getEmail())
    .ifPresent(()-> new RecursoDuplicadoexception("El email ya existe: " + request.getEmail()))
    ;

    
    UserEntity userCreate = new UserEntity();
    userCreate.setNombre(request.getNombre());
    userCreate.setEmail(request.getEmail());
    userCreate.setDireccion(request.getDireccion());
    userRepository.save(userCreate);

    UserResponseDTO response = new UserResponseDTO();
    response.setId(userCreate.getIdUsuario());
    response.setEmail(userCreate.getEmail());
    response.setNombre(userCreate.getNombre());
    response.setDireccion(userCreate.getDireccion());
    return response;
}

        // metodo para ver todos los usuarios


    public List<UserResponseDTO> seeAllUsers(){
        List<UserEntity> user = userRepository.findAll();
        List<UserResponseDTO> response = new ArrayList<>();
        for(UserEntity users :user){
            UserResponseDTO dto = new UserResponseDTO();
            dto.setDireccion(users.getDireccion());
            dto.setEmail(users.getEmail());
            dto.setNombre(users.getNombre());
            dto.setId(users.getIdUsuario());
            response.add(dto);
        }
        return response;
    }

        public UserResponseDTO seeUserByid(Long id)throws Exception{
            Optional<UserEntity> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()){
                UserEntity user = userOptional.get();
                UserResponseDTO response = new UserResponseDTO();
                response.setId(user.getIdUsuario());
                response.setEmail(user.getEmail());
                response.setNombre(user.getNombre());
                response.setDireccion(user.getDireccion());
                return response;
            } else {
                throw new Exception("Usuario no encontrado");
            }
        }

        //metodo para eliminar un usuario por su ID
        public String deleteUserByid (Long id) throws Exception{
            Optional<UserEntity> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()){
                userRepository.deleteById(id);
                return "Usuario eliminado correctamente";
                
            }
            else{
                throw new Exception("Usuario no encontrado");
            }
        }


        //metodo para actualizar un usuario por su ID
        public UserResponseDTO updateUserById(Long id, UserRequestDTO request)  {
            UserEntity idUser = userRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con id: " + id)); 
            

                UserEntity user = idUser;
                user.setNombre(request.getNombre());
                user.setEmail(request.getEmail());
                user.setDireccion(request.getDireccion());
                userRepository.save(user);

                UserResponseDTO response = new UserResponseDTO();
                response.setId(user.getIdUsuario());
                response.setEmail(user.getEmail());
                response.setNombre(user.getNombre());
                response.setDireccion(user.getDireccion());
                return response;
                
            
            
        }

    }




    









    

