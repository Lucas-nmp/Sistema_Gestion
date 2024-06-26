package com.sistema.Gestion.controller;

import com.sistema.Gestion.model.User;
import com.sistema.Gestion.service.UserService;
import com.sistema.Gestion.view.LoginPage;
import com.sistema.Gestion.view.NewUserPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lucas
 */
@Component
public class Controller implements ActionListener{
    
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private UserService userService;
    
    private LoginPage loginPage;
    private NewUserPage newUserPage;
    
    @Autowired
    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
        
        this.loginPage.getBtnExit().addActionListener(this);
        this.loginPage.getBtnLogin().addActionListener(this);
        this.loginPage.getBtnNewUser().addActionListener(this);
        
        
    }
    
    @Autowired
    public void setNewUserPage(NewUserPage newUserPage) {
        this.newUserPage = newUserPage;
        
        this.newUserPage.getBtnExit().addActionListener(this);
        this.newUserPage.getBtnAddUser().addActionListener(this);
    }
    
    
    public void viewLogin() {
        loginPage.setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Eventos LoginPage
        if (e.getSource() == loginPage.getBtnExit()) {
            loginPage.dispose();
        }
        
        if (e.getSource() == loginPage.getBtnLogin()) {
            String name = loginPage.getName();
            String pass = loginPage.getPass();
            AccessControl(name, pass);
            
            
        }
        
        if (e.getSource() == loginPage.getBtnNewUser()) {
            newUserPage.setLocationRelativeTo(null);
            newUserPage.setModal(true);
            newUserPage.setVisible(true);
           
        }
        
        // Eventos NewUserPage
        if (e.getSource() == newUserPage.getBtnExit()) {
            newUserPage.dispose();
            newUserPage.cleanAll();
        }
        
        if (e.getSource() == newUserPage.getBtnAddUser()) {
            String pass = newUserPage.getAdminPass();
            addUser(pass);
        }
        
        
    }

    private void addUser(String pass) {
        
        User userAdmin = userService.findUserById(1);
        String AdminPass = userAdmin.getPassword();
        if (AdminPass.equals(pass)) {
            String fullName = newUserPage.getFullName();
            String userName = newUserPage.getUserName();
            String phone = newUserPage.getPhone();
            String email = newUserPage.getEmail();
            String passwd = newUserPage.getPass();
            
            if (fullName.isEmpty() || userName.isEmpty() || phone.isEmpty() || passwd.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(newUserPage, "Todos los campos son obligatorios");
            } else {
                User newUser = new User(null, passwd, fullName, userName, phone, email);
                userService.addModifyUser(newUser);
                JOptionPane.showMessageDialog(newUserPage, "Usuario añadido correctamente");
                newUserPage.clean();
            }
             
        } else {
            JOptionPane.showMessageDialog(newUserPage, "Sólo puede añadir usuarios el Administrador");
        }
        
        
    }

    private void AccessControl(String name, String pass) {
        
        List<User> listUsers = userService.getAllUsers();
        boolean access = listUsers.stream().anyMatch(u -> u.getUserName().equals(name) && u.getPassword().equals(pass));
        
        
        if (access) {
            JOptionPane.showMessageDialog(loginPage, "Acceso concedido"); 
        } else {
            JOptionPane.showMessageDialog(loginPage, "Acceso denegado"); 
        }
        
    }

    
    
    
}
