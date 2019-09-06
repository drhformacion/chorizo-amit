package com.chorizo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class ChorizoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChorizoApplication.class, args);
	}

	@EnableWebSecurity // Provisional hasta implementacion de token
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable(); // Desactivacion csrf origin 
			http.cors();
		}
		
	}
}
