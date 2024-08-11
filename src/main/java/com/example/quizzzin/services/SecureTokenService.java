package com.example.quizzzin.services;

import com.example.quizzzin.models.entities.SecureToken;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.repositories.SecureTokenRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecureTokenService {
    private final SecureTokenRepository secureTokenRepository;
    private final UserService userService;

    @Value("${secure.token.validity}")
    private int tokenValidityInSeconds;

    @Value("${secure.token.key}")
    private String key;

    public SecureToken save(SecureToken secureToken) {
        return secureTokenRepository.save(secureToken);
    }

    public Optional<SecureToken> findByToken(String token) {
        return secureTokenRepository.findByToken(token);
    }

    @Transactional
    public Long deleteByToken(String token) {
        return secureTokenRepository.deleteByToken(token);
    }

    @Transactional
    public boolean verifyToken(String token) {
        Optional<SecureToken> secureToken = findByToken(token);

        boolean isVerified = secureToken.isPresent();

        if (isVerified) {
            User user = secureToken.get().getUser();
            user.setAccountVerified(true);

            userService.saveUser(user);
            deleteByToken(token);
        }

        return isVerified;
    }

    public SecureToken generateAndSaveSecureToken(User user) {
        String token = new String(Base64.getEncoder().encode(key.getBytes()));
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(tokenValidityInSeconds);

        return save(new SecureToken(token, expiration, user));
    }

}