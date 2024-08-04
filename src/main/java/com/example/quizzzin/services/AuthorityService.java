package com.example.quizzzin.services;

import com.example.quizzzin.enums.AuthorityType;
import com.example.quizzzin.models.entities.Authority;
import com.example.quizzzin.repositories.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The RoleService class provides operations for managing roles in the application.
 * This includes finding roles by their name.
 * <p>
 * Dependencies:
 * - {@link AuthorityRepository}: Repository for role persistence operations.
 */
@Service
@AllArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role to find.
     * @return The found {@link Authority} entity.
     */
    public Authority findByName(AuthorityType name) {
        return authorityRepository.findByName(name);
    }
}