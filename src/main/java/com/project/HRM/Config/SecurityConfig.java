package com.project.HRM.Config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.project.HRM.Security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

	 @Autowired
	    private JwtAuthenticationFilter filter;

	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


	        http.csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/employees/**").permitAll()
	                        .requestMatchers("/auth/**").permitAll()
	                        .anyRequest().authenticated())
	                .httpBasic(Customizer.withDefaults())
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
	    
	    @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }
	    
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }


}
