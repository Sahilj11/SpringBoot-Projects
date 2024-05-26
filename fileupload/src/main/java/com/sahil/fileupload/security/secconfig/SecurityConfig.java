package com.sahil.fileupload.security.secconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sahil.fileupload.security.jwt.JwtFilter;

/** SecurityConfig */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPoint authEntryPoint;
    private final JwtFilter jwtFilter;

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
                        .authenticated());

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint));
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
