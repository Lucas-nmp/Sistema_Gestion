/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.Gestion.service;

import com.sistema.Gestion.model.User;
import com.sistema.Gestion.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> viewUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        return user;
    }

    @Override
    public void addModifyUser(User user) {
        // de forma automática detecta si el id del usuario existe o no para modificarlo o crearlo nuevo
        userRepository.save(user);
        
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
        
        
    }
    
}
