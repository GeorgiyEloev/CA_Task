package com.example.serverForCA.config;

import com.example.serverForCA.security.jwt.JwtConfigurer;
import com.example.serverForCA.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationConfiguration authConfiguration;


  @Autowired
  public SecurityConfig(JwtTokenProvider jwtTokenProvider, AuthenticationConfiguration authConfiguration) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.authConfiguration = authConfiguration;
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(
                    "/api/v1/auth/*",
                    "/swagger-ui/**" ,
                    "/swagger-ui.html",
                    "/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    return http.build();
  }
}
