package com.example.serverForCA.exceptions.exception_config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

public class ExceptionHandlerConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  private final FilterChainExceptionHandler filterChainExceptionHandler;

  public ExceptionHandlerConfigurer(FilterChainExceptionHandler filterChainExceptionHandler) {
    this.filterChainExceptionHandler = filterChainExceptionHandler;
  }

  @Override
  public void configure(HttpSecurity httpSecurity) {
    httpSecurity.addFilterBefore(filterChainExceptionHandler, DisableEncodeUrlFilter.class);
  }
}
