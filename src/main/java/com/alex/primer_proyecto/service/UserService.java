package com.alex.primer_proyecto.service;




import java.util.ArrayList;
import java.util.List;



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
 userRepository.findFirstByEmail(request.getEmail())
    .ifPresent(email -> {
        throw new RecursoDuplicadoexception("El email ya existe: " + request.getEmail());
    });

    
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

        public UserResponseDTO seeUserByid(Long id){
            return userRepository.findById(id)
            .map(user -> {
            
            
                UserResponseDTO response = new UserResponseDTO();
                response.setId(user.getIdUsuario());
                response.setEmail(user.getEmail());
                response.setNombre(user.getNombre());
                response.setDireccion(user.getDireccion());
                return response;
                    })
                    .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con id: " + id));
                }

        //metodo para eliminar un usuario por su ID
        public String deleteUserByid (Long id) {
         return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return "Usuario eliminado con exito";
            })
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con id: " + id));
            }
            
        


        //metodo para actualizar un usuario por su ID
        public UserResponseDTO updateUserById(Long id, UserRequestDTO request)  {
              UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con id: " + id));
             
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




    









    

