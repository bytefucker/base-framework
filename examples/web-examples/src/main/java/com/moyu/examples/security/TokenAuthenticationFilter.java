package com.moyu.examples.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * TokenAuthenticationFilter
 *
 * @author yhz
 * @date 2023/3/30
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationEntryPoint authenticationEntryPoint;
  private final AuthenticationManager authenticationManager;


  public TokenAuthenticationFilter(
      AuthenticationEntryPoint authenticationEntryPoint,
      AuthenticationManager authenticationManager) {
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

  }
}
