/**
 * Entidad Usuario
 */
package com.example.PruebaAuthSpring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author char_
 */
@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String country;
    private String firstname;
    private String lastname;
    private String password;
    private Enum role;
    private String username;
}
