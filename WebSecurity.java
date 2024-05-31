/**
 * Clase de autenticación y autorización
 * Se definen que recursos deben de estar securizados y cuales no lo deben estar
 */
package com.example.PruebaAuthSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author char_
 */
@EnableWebSecurity
@Configuration
public class WebSecurity {

    @Autowired
    private JwtAuthorizationFilter JwtAuthorizationFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/publico").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/privado").authenticated()
                .anyRequest().authenticated()
                .and().addFilterBefore(JwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)// Añadimos el filtro
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Creamos una política sin estado
        //.httpBasic(withDefaults())
        //.formLogin();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
