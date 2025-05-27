package com.benestorff.FitLog_spring_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa CSRF para facilitar testes (rever em produção)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users", "/auth/**").permitAll() // Libera cadastro e login
                .anyRequest().authenticated() // Protege o resto
            );

        return http.build();
    }
}