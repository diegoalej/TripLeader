package com.downstreammedia.sandbar.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class configures spring security security details
 * 
 * @author Diego Hoyos
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Autowired
	 private PasswordEncoder encoder;
	 
     @Autowired
     private DataSource dataSource;
	
 	/**
 	 * Method establishes endpoints to authenticate
 	 * and type of security to follow
 	 * 
 	 * @return - a list of all users
 	 */
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http
		 		.csrf().disable()
		 		.authorizeRequests()
		 		.antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
		 		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
		 		.antMatchers(HttpMethod.GET, "/api/**").permitAll() 
		 		.antMatchers("*/*", "/css/*" ,"/api/users",  "/authenticate" , "js/*").permitAll()
//		 		.anyRequest().authenticated()
		 		.antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
		        .anyRequest().permitAll()               // All other requests are allowed without authorization.
		 		.and()
		 		.httpBasic();
		 
		 http
		        .sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 }
	
 	/**
 	 * Method establishes configuration for 
 	 * authenticating users at login
 	 * 
 	 * @return - a list of all users
 	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		String userQuery = "SELECT username, password, active FROM User WHERE username=?";
        String authQuery = "SELECT username, role FROM User WHERE username=?";
        auth
	        .jdbcAuthentication()
	        .dataSource(dataSource)
	        .usersByUsernameQuery(userQuery)
	        .authoritiesByUsernameQuery(authQuery)
	        .passwordEncoder(encoder);
	}
}
