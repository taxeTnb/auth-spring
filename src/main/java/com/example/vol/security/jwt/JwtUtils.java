package com.example.vol.security.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import com.example.vol.models.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;



@Component
public class JwtUtils {

  private final String JWT_SECRET;
  private final String JWT_BEARER = "bearer ";
  private final Long ACCESS_TOKEN_EXPIRATION_MN;
  private final Long REFRECH_TOKEN_EXPIRATION_MN;
  public JwtUtils(
          @Value("${sirhat.jwt.secret}") String JWT_SECRET,
          @Value("${sirhat.jwt.token.expiration_access_mn}") Long ACCESS_TOKEN_EXPIRATION_MN,
          @Value("${sirhat.jwt.token.expiration_refresh_mn}")Long REFRECH_TOKEN_EXPIRATION_MN
  ) {
    this.JWT_SECRET = JWT_SECRET;
    this.ACCESS_TOKEN_EXPIRATION_MN = ACCESS_TOKEN_EXPIRATION_MN;
    this.REFRECH_TOKEN_EXPIRATION_MN = REFRECH_TOKEN_EXPIRATION_MN;
  }

  public Token generateToken(String username){
    Instant instant = Instant.now();
    return new Token(
            Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(Date.from(instant))
                    .setIssuer("vol-service")
                    .setExpiration(Date.from(instant.plus(ACCESS_TOKEN_EXPIRATION_MN, ChronoUnit.MINUTES)))
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                    .compact()
    );

  }

  public Claims getClaimsFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
  }

  public String getIdFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getId();
  }

public String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }
    return null;
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      System.err.println("Invalid JWT signature: "+ e.getMessage());
    } catch (MalformedJwtException e) {
      System.err.println("Invalid JWT token: "+ e.getMessage());
    } catch (ExpiredJwtException e) {
      System.err.println("JWT token is expired: "+ e.getMessage());
    } catch (UnsupportedJwtException e) {
      System.err.println("JWT token is unsupported: "+ e.getMessage());
    } catch (IllegalArgumentException e) {
      System.err.println("JWT claims string is empty: "+ e.getMessage());
    }
    return false;
  }

}
