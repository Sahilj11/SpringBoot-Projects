package com.sahil.fileupload.security.secconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

/** SecurityConfig */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                req -> req.requestMatchers("/api/auth/login/**")
                        .permitAll()
                        .requestMatchers("/api/auth/signup/**")
                        .permitAll()
                        .anyRequest()
                        .hasAuthority("FREE"));

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint));
        // http.formLogin(login-> login.loginPage("/api/auth/login").permitAll());
        return http.build();
    }
}
