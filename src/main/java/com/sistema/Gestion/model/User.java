package com.sistema.Gestion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Lucas
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String fullName;
    private String userName;
    private String telefon;
    private String email;
    
}
