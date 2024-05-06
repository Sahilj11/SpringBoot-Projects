package com.example.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

/** SecurityConfig */
@Configuration
public class SecurityConfig {

  @Bean
  @Order(1)
  public SecurityFilterChain asecurityFilterChain(HttpSecurity http) throws Exception {
    http.exceptionHandling(
        c ->
            c.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));
    return http.build();
  }

  @Bean
  @Order
  public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(Customizer.withDefaults());
    http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
    return http.build();
  }
}
