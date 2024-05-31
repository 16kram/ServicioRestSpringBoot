/**
 * Controlador de Usuario
 */
package com.example.PruebaAuthSpring;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author char_
 */
@RestController
@RequiredArgsConstructor
public class UsuariosController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService myUserDetailService;
    private final JWTService jwtService;

    @Autowired
    UsuariosService usuariosService;

    @GetMapping("/publico")
    public String publico() {
        return "Hola desde público";
    }

    @GetMapping("/privado")
    public String privado() {
        return "Hola desde privado";
    }

    @GetMapping("/listarusuarios")
    public List<Usuario> listarUsuarios() {
        return usuariosService.listarUsuarios();
    }

    /**
     * Controlador de login. Se crea un objeto
     * UsernamePasswordAuthenticationToken con la petición POST que contiene el
     * usuario y la contraseña, se llama a
     * authenticationManager.authenticate(authentication) para su validación,
     * ssi falla se crea una excepción, en caso contrario se crea el token y se
     * devuelve como respuesta al usuario.
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("El usuario o la contraseña no coinciden", e);
        }
        UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtService.createToken(userDetails);
        return new AuthenticationResponse(token);
    }

}
