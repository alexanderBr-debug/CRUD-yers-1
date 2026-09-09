package com.alex.primer_proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/*aqui le decimos a spring que esta clase va hacer una clase de configuracion("igual que con el @controller,@service"),
con esta anotacion spring sabra que en esta clase van a estar metodos anotados con @bean que debe ejecutar,construir y guardar cuando 
hagamos una instacia en una clase distinta
*/
@Configuration 
public class securityConfig {

    /*esta anotacion es muy util ya que nos permite instanciar o inyectar el objeto en cualquiera otra clase
    ej = cuando instanciamos repository en servise */
    @Bean 

    /*para este metodo tenemos que inicializar una dependecia especial,cuando tengamos esa depencia esta nos permitira
    acceder a unos metodos especiales,PassWordEncoder es una interfaz que nos proporciona la dependecia descxargada 
    lo que hace es abstraer la forma en la que se encriptan las contraseñas,el passWordEncoder es el nombre del metodo y tambien del bean
    */
    public PasswordEncoder passwordEncoder() {

        //aqui usamos Bcrypt lo que hace es hachear la contraseña de una forma segura
        return new BCryptPasswordEncoder();
    }
    
}
