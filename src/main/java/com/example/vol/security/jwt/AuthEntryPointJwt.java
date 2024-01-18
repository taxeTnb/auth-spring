package com.example.vol.security.jwt;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  private final ObjectMapper mapper;

  public AuthEntryPointJwt(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    mapper.writeValue(
            response.getOutputStream(),
            Map.of("status", HttpServletResponse.SC_UNAUTHORIZED,
            "error", "Unauthorized",
            "message", authException.getMessage(),
            "path", request.getServletPath())
    );

  }

}
