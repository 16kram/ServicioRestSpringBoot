/**
 * Repositorio del usuario
 */
package com.example.PruebaAuthSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author char_
 */
@Repository
public interface UserRepository extends JpaRepository<Usuario,Integer>{
   
    Usuario findByUsername(String nombre);
}
