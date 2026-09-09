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


/*aqui crearemos el controller para el login pero porque? aqui co menzamos con jwt */
@RestController 
@AllArgsConstructor 
@RequestMapping ("/login")
public class authController {

   //instanciamos las objetos requeridos
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private  final AutorRepository autorRepository;

    //ruta o url
    @PostMapping ("/hola")

    /*  aqui creamos un metodo de retorno,que va a retornar un token.que es un token? un token es artefacto de autenticacion digital 
    firmado criptograficamente que encapsula la identidad y credenciales del ususrio(luego vemos mas a fondo eso) pero basicamente eso haremos
    @vali... verifica o valida(la misma cosa) algunos requerimiebtos puestos en el json y lo hace antes de que el requesbody lo
    convierta a dto */
    public ResponseEntity<LoginResponseDTO> login( @Validated @RequestBody LoginRequestDTO request){

      //simplemente una validacion si el autor exixte o no y sino manda una exception
    Autor autor =  autorRepository.findFirstByEmail(request.getEmail())
         .orElseThrow(() -> new RecursoNoEncontradoException("usuario no encontrado"));

         /*aqui otra validacion verificamos que la contraseña sea correcta y lo hacemos con matches
         (lo que hace es comparar el hasheo)en pocas palabras lo que hace es cuando tu pones una contraseña
         con el metodo que ya vimos en el config la hasheamos y cuando la vuelves a ingresar matches se
         encarga de comparar sin reversir el hasheo por eso necesita esos dos argumentos(luego le explico mejor)*/
         if(!passwordEncoder.matches(request.getPassWord(), autor.getPassWord())){
            throw new CredencialesInvalidasException("contraseña incorrecta");
         }

         /*si todo sale bien le creamos una instancia y se la mandamos al "generador de tokens" por llamarlo de una manera
         con el email y por que con el email? porque el email es un identificador unico por eso lo ponemos ya que u  token 
         tiene 3 partes separadas por puntos y una de ellas es un identificar del usuario*/
         String token = jwtService.generarToken(autor.getEmail());

         //guardamos ese token es un responsedto y se lo envimos al cloiemte
         LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
            
}
}
