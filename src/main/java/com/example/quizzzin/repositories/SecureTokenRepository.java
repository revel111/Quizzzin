package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.SecureToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecureTokenRepository extends CrudRepository<SecureToken, Long> {
    Optional<SecureToken> findByToken(String token);

    Long deleteByToken(String token);
}