package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.SecureToken;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SecureTokenRepository extends CrudRepository<SecureToken, Long> {
    Optional<SecureToken> findByToken(String token);

//    Long deleteByToken(String token);

    List<SecureToken> findByDateOfExpirationBefore(LocalDateTime dateOfExpiration);
}