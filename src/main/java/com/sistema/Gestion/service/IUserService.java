package com.sistema.Gestion.service;

import com.sistema.Gestion.model.User;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface IUserService {
    
    public List<User> getAllUsers();
    
    public User findUserById(Integer idUser);
    
    public void addModifyUser(User user);
    
    public void deleteUser(User user);
    
}
