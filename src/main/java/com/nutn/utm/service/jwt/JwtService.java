package com.nutn.utm.service.jwt;

import com.nutn.utm.model.dto.response.JwtAuthResponseDto;
import com.nutn.utm.model.entity.Pilot;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;

import java.util.Date;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public interface JwtService {

    String createToken(Pilot pilot);

    String extractClaim(String token, String claimKey);

    JwtAuthResponseDto validateToken(String token);

    Authentication getAuthentication(String token);
}
