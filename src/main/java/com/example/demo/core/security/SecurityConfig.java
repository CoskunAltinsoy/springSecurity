package com.example.demo.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig{
	
	public final CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}
	
		
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		    return http.csrf().disable()
		    		.authorizeRequests(auth -> auth.antMatchers("api/users/*").permitAll()
		    	    .antMatchers("api/home/user").hasAnyRole("USER")
		    	    .antMatchers("api/home/admin").hasAnyRole("ADMIN")
		    		.anyRequest().authenticated()).exceptionHandling().accessDeniedPage("/error/403")
		    		.and()
		    		.userDetailsService(customUserDetailsService)
		    		.httpBasic(Customizer.withDefaults())
		    		.build();  
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
