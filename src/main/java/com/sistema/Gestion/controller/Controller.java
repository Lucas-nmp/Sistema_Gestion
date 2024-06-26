package com.sistema.Gestion.controller;

import com.sistema.Gestion.service.UserService;
import com.sistema.Gestion.view.LoginPage;
import com.sistema.Gestion.view.NewUserPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            JOptionPane.showMessageDialog(loginPage, "Login user");
            
        }
        
        if (e.getSource() == loginPage.getBtnNewUser()) {
            newUserPage.setLocationRelativeTo(null);
            newUserPage.setModal(true);
            newUserPage.setVisible(true);
            
            //JOptionPane.showMessageDialog(loginPage, "insert new User");
        }
        
        // Eventos NewUserPage
        if (e.getSource() == newUserPage.getBtnExit()) {
            newUserPage.dispose();
        }
        
        if (e.getSource() == newUserPage.getBtnAddUser()) {
            addUser();
        }
        
        
    }

    private void addUser() {
        
    }
    
    
}
