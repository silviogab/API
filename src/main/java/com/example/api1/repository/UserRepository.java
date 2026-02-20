package com.example.api1.repository;

import com.example.api1.domain.User;  // ⚠ veja se está domain mesmo
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}


