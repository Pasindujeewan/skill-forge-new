package com.skillforge.authservice.service;

import com.skillforge.authservice.client.UserServiceClient;
import com.skillforge.authservice.dto.LoginRequest;
import com.skillforge.authservice.dto.RegisterRequest;
import com.skillforge.authservice.dto.TokenResponse;
import com.skillforge.authservice.entity.AuthAccount;
import com.skillforge.authservice.enums.AccountStatus;
import com.skillforge.authservice.repository.AuthAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@Service
public class AuthService {

    private final AuthAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserServiceClient userServiceClient;

    public AuthService(
            AuthAccountRepository accountRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            UserServiceClient userServiceClient
    ) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userServiceClient = userServiceClient;
    }

    @Transactional
    public TokenResponse register(RegisterRequest request) {
        String email = normalizeEmail(request.email());
        if (accountRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }

        AuthAccount account = accountRepository.saveAndFlush(
                new AuthAccount(email, passwordEncoder.encode(request.password()))
        );
        userServiceClient.createProfile(
                account.getId(),
                email,
                request.firstName().trim(),
                request.lastName().trim()
        );
        return tokenResponse(account);
    }

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        AuthAccount account = accountRepository.findByEmail(normalizeEmail(request.email()))
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), account.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new BadCredentialsException("Account is not active");
        }
        return tokenResponse(account);
    }

    private TokenResponse tokenResponse(AuthAccount account) {
        return new TokenResponse(
                jwtService.generateAccessToken(account.getId()),
                "Bearer",
                jwtService.getExpirationSeconds()
        );
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
