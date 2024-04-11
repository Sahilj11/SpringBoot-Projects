package com.sahil.fileupload.security;

import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/**
 * SecurityConfig
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final  AuthenticationEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(req -> req.requestMatchers("/api/auth/login/**").permitAll()
                .requestMatchers("/api/auth/signup/**").permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint));
        return http.build();
    }
}
