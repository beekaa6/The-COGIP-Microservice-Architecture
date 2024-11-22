package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
              .csrf(customizer -> customizer.disable())
              .authorizeHttpRequests(request -> request
              .requestMatchers("/user/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.POST, "/companies/**", "/invoice/**", "/contact/**").hasAnyRole("ADMIN", "ACCOUNTANT")
              .requestMatchers(HttpMethod.PUT, "/companies/**", "/invoice/**", "/contact/**").hasAnyRole("ADMIN", "ACCOUNTANT")
              .requestMatchers(HttpMethod.DELETE, "/companies/**", "/invoice/**", "/contact/**").hasAnyRole("ADMIN", "ACCOUNTANT")
              .requestMatchers(HttpMethod.GET, "/companies/**", "/invoice/**", "/contact/**").hasAnyRole("ADMIN", "ACCOUNTANT", "INTERN")
              .anyRequest().authenticated())
              //.formLogin(Customizer.withDefaults())
              .httpBasic(Customizer.withDefaults())
              .sessionManagement(session -> 
                  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){

    UserDetails beka = User.withUsername("beka")
        .password(passwordEncoder.encode("test"))
        .roles("ADMIN")
        .build();

    UserDetails kasoy = User.withUsername("kasoy")
        .password(passwordEncoder.encode("test"))
        .roles("ACCOUNTANT")
        .build();
    
    UserDetails user = User.withUsername("user")
        .password(passwordEncoder.encode("test"))
        .roles("INTERN")
        .build();
    return new InMemoryUserDetailsManager(beka, kasoy, user);
  }
}
