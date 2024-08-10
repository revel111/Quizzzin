package com.example.quizzzin.services;

import com.example.quizzzin.enums.AuthorityType;
import com.example.quizzzin.mappers.other.UserMapper;
import com.example.quizzzin.models.dto.other.RegisterUserDTO;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The UserService class provides operations for managing users in the application.
 * This includes user registration, authentication retrieval, and user lookup by ID or email.
 * <p>
 * Dependencies:
 * - {@link UserRepository}: Repository for user persistence operations.
 * - {@link AuthorityService}: Service for managing user roles.
 * - {@link UserMapper}: Mapper for converting between User entities and DTOs.
 * - {@link PasswordEncoder}: Utility for encoding user passwords.
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user in the application.
     * Converts the provided RegisterUserDTO to a User entity, encodes the user's password,
     * assigns the default role, and saves the user to the repository.
     *
     * @param registerUserDTO The data transfer object containing registration details.
     * @return The saved User entity.
     */
    public User registerUser(RegisterUserDTO registerUserDTO) {
        User user = userMapper.toUser(registerUserDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getAuthoritiesList().add(authorityService.findByName(AuthorityType.USER));

        return saveUser(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves the currently authenticated user from the security context.
     *
     * @return The authenticated User entity, or null if no user is authenticated.
     */
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
            return (User) authentication.getPrincipal();

        return null;
    }

    /**
     * Retrieves the ID of the currently authenticated user.
     *
     * @return The ID of the authenticated user, or null if no user is authenticated.
     */
    public Long getAuthenticatedUserId() {
        User user = getAuthenticatedUser();

        if (user == null)
            return null;

        return user.getId();
    }

    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to find.
     * @return An Optional containing the found User entity, or empty if not found.
     */
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return An Optional containing the found User entity, or empty if not found.
     */
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void forgotPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
    }
}