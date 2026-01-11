package todoList.maker.todoList.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
@Component
public class JwtUtils {
   private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   private final int jwtExpirationMs = 86400000;
   public String generateToken(String username) {
	   return Jwts.builder()
			   .setSubject(username)
			   .setIssuedAt(new Date())
			   .setExpiration(new Date(System.currentTimeMillis() +jwtExpirationMs))
               .signWith(key)
               .compact();
   }
   public String getUsernameFromToken(String token) {
	   return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
   }
   public boolean ValidateToken(String token) {
	   try {
		   Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		   return true;
	   } catch (JwtException | IllegalArgumentException e) {
		   System.out.println("Invalid Jwt"+e.getMessage());
	   }
	   return false;
   }
}
