package com.downstreammedia.sandbar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Main class that bootstraps Spring application
 * 
 * @author Diego Hoyos
 */
@SpringBootApplication
public class ConfluenceApplication {
	
	  /**
	   * Launches the application
	   * 
	   * @param args - Application startup arguments
	   */
	public static void main(String[] args) {
		SpringApplication.run(ConfluenceApplication.class, args);
	}
	
	  /**
	   * returns password encoder bean
	   * 
	   * @return a new password encoder
	   */
	@Bean
	public PasswordEncoder configurePasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
