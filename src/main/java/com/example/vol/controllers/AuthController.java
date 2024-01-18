package com.example.vol.controllers;

import com.example.vol.models.AuthResponse;
import com.example.vol.models.LoginRequest;
import com.example.vol.models.Token;
import com.example.vol.models.Utilisateur;
import com.example.vol.repositories.AppUserRepository;
import com.example.vol.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private PasswordEncoder passwordEncoder;

  //private RestTemplate restTemplate;


  @Autowired
  AppUserRepository userRepository;

  public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    Token token = jwtUtils.generateToken(loginRequest.getUsername());

    Utilisateur user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));


    AuthResponse authResponse = new AuthResponse(token.getAccessToken(), user);

    return ResponseEntity.ok(authResponse);

  }

  @PostMapping("/register")
  public ResponseEntity<Map<String, Object>> register(@RequestBody Utilisateur utilisateur) {
    Utilisateur existingUser = userRepository.findByUsername(utilisateur.getUsername()).orElse(null);
    Map<String, Object> response = new HashMap<>();

    System.out.println(utilisateur.getPassword());
    if (existingUser == null) {
      utilisateur.setRole("clt");
      utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
      userRepository.save(utilisateur);

      response.put("message", "Success");
      response.put("status", HttpStatus.OK.value());
      return ResponseEntity.ok(response);
    }

    response.put("message", "FAILED");
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
