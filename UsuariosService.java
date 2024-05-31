/**
 * Clase de servicio del usuario
 */
package com.example.PruebaAuthSpring;

import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author char_
 */
@Service
@NoArgsConstructor
public class UsuariosService {

    @Autowired
    UserRepository userRepository;

    public List<Usuario> listarUsuarios() {
        return userRepository.findAll();
    }

}
