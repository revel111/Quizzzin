package com.example.quizzzin.configurations;

import com.example.quizzzin.models.dto.other.CredentialsDTO;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

/**
 * DON'T USE!
 * @deprecated
 */
@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final String SECRET = "secret";

    public User authenticate(CredentialsDTO credentialsDTO) {
        Optional<User> user = userService.findUserByEmail(credentialsDTO.email());

        if (user.isPresent() &&
                passwordEncoder.matches(credentialsDTO.password(), user.get().getPassword()))
            return user.get();

        throw new UsernameNotFoundException("User with email '" + credentialsDTO.email() + "' passed wrong password");
    }

    public User findByEmail(String email) {
        return userService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' not found"));
    }

    public String generateToken(User user) {
        return user.getId() + "&" + user.getEmail() + "&" + calculateHmac(user);
    }

    public User findByToken(String token) {
        String[] part = token.split("&");

        long userId = Long.parseLong(part[0]);
        String email = part[1];
        String hmac = part[2];

        User user = findByEmail(email);

        if (!hmac.equals(calculateHmac(user)) ||
                userId != user.getId())
            throw new UsernameNotFoundException("Invalid cookie value.");

        return user;
    }

    private String calculateHmac(User user) {
        byte[] secretKeyBytes = Objects.requireNonNull(SECRET)
                .getBytes(StandardCharsets.UTF_8);
        byte[] valueBytes = (user.getId() + "&" + user.getEmail())
                .getBytes(StandardCharsets.UTF_8);

        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec sec = new SecretKeySpec(secretKeyBytes, "HmacSHA512");

            mac.init(sec);
            byte[] hmacBytes = mac.doFinal(valueBytes);

            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}