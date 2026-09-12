package com.alex.primer_proyecto.config;

import com.alex.primer_proyecto.security.JwtAuthenticationFiltre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;


/*lo que haremos en esta clase va ser configuraciones globales,cuando iniciamos la depedencia para spring security esta nos
"bloquea" o mejor dicho pone en privada todos en endpois(rutas)de nustro proyecto,lo que significa que ya podremos mandar peticiones 
por postam sin embargo  nos permite decidir cuales rutas poner publicas,eso es lo que haremos en este archivo
*/
@Configuration 
@RequiredArgsConstructor 
public class securityFiltre {

    //aqui registramos nuestro metodo con un bean y nos aseguramos de que cumpla las reglas http a cada peticion entrante.
     
    private final JwtAuthenticationFiltre jwtAuthenticationFiltre;

    @Bean 
    /*securityFiltre... es la interfas que representa la cadena de filtros de seguridad por donde pasara la solisitud,
    security... es una de filtros por defecto de spring sin embargo  podemos cambiar esa cadena por medio de una herramienta 
    llamada HttpSecurity que nos permite modificar los filtros o agregar y retornamos la configuracion completade de seguridad
    que es securityFiltrechain*/

    /* y http es el parametro para poder modificar esos filtros */


    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            /*desactivamos la protecciomn csrf que es es csrf,es una proteccion para sitios wed que dependen de cookis
            (lo que hace es proteccion contra falsificacion de peticiones),
            por lo que csrf no es necesaria y la desactivamos para evitar bloqueos */
            .csrf(csrf -> csrf.disable())

            /*aqui le decimo a spring q no guarde seccion de usuarios tradicionales como los cookis ya que con jwt
            cada peticion se autentica por si sola con el token que trae */
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            

            /*aqui podemos modificar cuales rutas van hacer publicas(la unica que debe ser publica es el login)
            comenzamos con authorizeHttpRequests lo que hace es iniciar la definicion de las reglas de acceso,
              requestMatchers lo unico que hace es definir una exception publica(simplemente dice"a esta ruta no 
              necesitas autenticacion"*/
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login/hola").permitAll()

                //simplemente decimos que las demas rutas si necesitaran autenticacion
                .anyRequest().authenticated()
            )

            /*aqui añadimos nuestro filtro y le decimo que lo ejecute antes de l primer filtro por defecto que en este caso 
            es username... y asi registramos nuestro filtro ala cadena */
            .addFilterBefore(jwtAuthenticationFiltre,UsernamePasswordAuthenticationFilter.class);

            //simplemente emsambla o junta todas las modificaciones hechas anteriormente y retorna el objeto
        return http.build();
    }
    
}
