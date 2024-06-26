package com.sistema.Gestion;

import com.sistema.Gestion.controller.Controller;
import com.sistema.Gestion.view.LoginPage;
import java.awt.EventQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GestionApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext contextoSpring = new SpringApplicationBuilder(GestionApplication.class)
                    .headless(false)
                    .web(WebApplicationType.NONE)
                    .run(args);
                /*
                EventQueue.invokeLater(() -> {
                    LoginPage loginPage = contextoSpring.getBean(LoginPage.class);
                    loginPage.setVisible(true);
                }); */
                
                EventQueue.invokeLater(() -> {
                    Controller controller = contextoSpring.getBean(Controller.class);
                    controller.viewLogin();
                });
	}

}
