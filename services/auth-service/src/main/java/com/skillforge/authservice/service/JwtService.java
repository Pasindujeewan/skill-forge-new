package com.skillforge.authservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final long expirationMillis;

    public JwtService(
            JwtEncoder jwtEncoder,
            @Value("${security.jwt.expiration:900000}") long expirationMillis
    ) {
        if (expirationMillis <= 0) {
            throw new IllegalArgumentException("security.jwt.expiration must be positive");
        }
        this.jwtEncoder = jwtEncoder;
        this.expirationMillis = expirationMillis;
    }

    public String generateAccessToken(UUID accountId) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(accountId.toString())
                .issuedAt(now)
                .expiresAt(now.plusMillis(expirationMillis))
                .claim("token_type", "access")
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public long getExpirationSeconds() {
        return expirationMillis / 1000;
    }
}
