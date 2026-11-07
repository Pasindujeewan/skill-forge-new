package com.skillforge.authservice.repository;

import com.skillforge.authservice.entity.AuthAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthAccountRepository extends JpaRepository<AuthAccount, UUID> {

    Optional<AuthAccount> findByEmail(String email);

    boolean existsByEmail(String email);
}
