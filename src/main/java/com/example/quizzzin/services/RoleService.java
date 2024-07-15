package com.example.quizzzin.services;

import com.example.quizzzin.enums.RoleType;
import com.example.quizzzin.models.entities.Role;
import com.example.quizzzin.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(RoleType name) {
        return roleRepository.findByName(name);
    }
}