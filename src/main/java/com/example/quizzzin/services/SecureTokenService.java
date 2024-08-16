package com.example.quizzzin.services;

import com.example.quizzzin.models.dto.other.PasswordRecoveringDTO;
import com.example.quizzzin.models.entities.SecureToken;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.repositories.SecureTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecureTokenService {
    private final SecureTokenRepository secureTokenRepository;
    private final UserService userService;

    @Value("${secure.token.validity}")
    private int tokenValidityInMilliseconds;

    public SecureToken save(SecureToken secureToken) {
        return secureTokenRepository.save(secureToken);
    }

    public Optional<SecureToken> findByToken(String token) {
        return secureTokenRepository.findByToken(token);
    }

    @Transactional
    public void deleteByToken(SecureToken token) {
        secureTokenRepository.delete(token);
    }

    @Transactional
    public Long deleteByToken(String token) {
        return secureTokenRepository.deleteByToken(token);
    }

    private List<SecureToken> findByDateOfExpirationBefore(LocalDateTime localDateTime) {
        return secureTokenRepository.findByDateOfExpirationBefore(localDateTime);
    }

    @Transactional
    public boolean verifyToken(String token) {
        Optional<SecureToken> secureToken = findByToken(token);

        boolean isVerified = secureToken.isPresent();

        if (isVerified) {
            User user = secureToken.get().getUser();
            user.setAccountVerified(true);

            userService.saveUser(user);
            deleteByToken(secureToken.get());
        }

        return isVerified;
    }

    public SecureToken generateAndSaveSecureToken(User user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(tokenValidityInMilliseconds / 1000);

        return save(new SecureToken(token, expiration, user));
    }

    @Transactional
    public User changePassword(PasswordRecoveringDTO passwordRecoveringDTO) {
        User user = userService.changePassword(
                findByToken(passwordRecoveringDTO.getToken()).get().getUser(), passwordRecoveringDTO.getPassword());

        deleteByToken(passwordRecoveringDTO.getToken());

        return user;
    }

    @Scheduled(fixedDelayString = "${secure.token.validity}") //delete expired tokens once per configured time.
    @Transactional
    public void checkAndHandleExpiredTokens() {
        findByDateOfExpirationBefore(LocalDateTime.now()).stream()
                .peek(secureToken -> {
                    User user = secureToken.getUser();
                    if (!user.isEnabled())
                        userService.deleteUser(user);
                })
                .forEach(this::deleteByToken);
    }
}