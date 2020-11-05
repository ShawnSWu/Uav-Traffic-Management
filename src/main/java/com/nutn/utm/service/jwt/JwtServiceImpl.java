package com.nutn.utm.service.jwt;

import com.nutn.utm.model.dto.response.JwtAuthResponseDto;
import com.nutn.utm.model.dto.response.message.AuthenticationMessage;
import com.nutn.utm.model.entity.Pilot;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Service
public class JwtServiceImpl implements JwtService{

    final static ResourceBundle resource = ResourceBundle.getBundle("secret");
    final static String secret = resource.getString("jwt.secret");
    static final long expirationTime = 86400000;//millisecond, 1 Day.
    public final static String ACCOUNT_ID = "accountId";

    public String createToken(Pilot pilot) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim(ACCOUNT_ID, pilot.getId())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build().parseClaimsJws(token).getBody();
    }

    public String extractClaim(String token, String claimKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(claimKey).toString();
    }

    public JwtAuthResponseDto validateToken(String token) {
        String message = AuthenticationMessage.JWT_AUTHORIZATION_OK;
        HttpStatus status = HttpStatus.OK;
        if (token.equals(""))
            return new JwtAuthResponseDto(AuthenticationMessage.INVALID_MESSAGE, HttpStatus.UNAUTHORIZED);

        try {
            extractClaim(token, ACCOUNT_ID);
        } catch (ExpiredJwtException e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            message = AuthenticationMessage.EXPIRED_MESSAGE;
            status = HttpStatus.UNAUTHORIZED;
        } catch (JwtException e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            message = AuthenticationMessage.INVALID_MESSAGE;
            status = HttpStatus.UNAUTHORIZED;
        }
        return new JwtAuthResponseDto(message, status);
    }

    public Authentication getAuthentication(String token) {
        String accountId = extractClaim(token, ACCOUNT_ID);
        return new UsernamePasswordAuthenticationToken(accountId, null, null);
    }
}
