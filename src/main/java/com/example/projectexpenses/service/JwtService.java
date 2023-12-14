package com.example.projectexpenses.service;

import com.example.projectexpenses.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

     @Value("${app.secret-key}")
     private String SECRET_KEY;

     @Value("${app.authentication-token-expires-minutes}")
     private Integer authenticationTokenExpiresMinutes;
//
//    @Value("${app.authentication-token-expires-days")
      private Integer authenticationTokenExpiresDays;

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    public Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token, Function<Claims, T > claimsResolver) throws JwtException {
         final Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
    }

    public Boolean isTokenExpired(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) throws JwtException{
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token){
      return extractClaim(token, Claims::getSubject);

//         Claims claims = extractAllClaims(token);
//         return claims.get("email").toString();
    }

    public Boolean validateToken(String token, UserDetails userDetails){

       final String username = extractUsername(token);

       return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

//    public String generateToken(User userDetails){
////        Map<String, Object> claims = new HashMap<>();
////
////        return createToken(claims, userDetails);
//        return generateToken(new HashMap<>(), userDetails);
//    }

//    public String generateToken(Map<String, Object> claims, User user){
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .setSubject(user.getEmail())
//                .claim("email", claims)
//                .setIssuedAt(new Date())
//                .setExpiration(Date.from(ZonedDateTime.now().plusDays(authenticationTokenExpiresDays).toInstant()))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }


    public String createToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .claim("email", userDetails.getUsername())
                .setIssuedAt(new Date())
                .setIssuer(userDetails.getUsername())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .setExpiration(Date.from(ZonedDateTime.now()
                        .plusDays(authenticationTokenExpiresMinutes).toInstant()))
                .compact();

    }
    public String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;

    }
}
