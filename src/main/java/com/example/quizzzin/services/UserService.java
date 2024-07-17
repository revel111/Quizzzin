package com.example.quizzzin.services;

import com.example.quizzzin.enums.RoleType;
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

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(RegisterUserDTO registerUserDTO) {
        User user = userMapper.toUser(registerUserDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.findByName(RoleType.USER));
        return userRepository.save(user);
    }

    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        return null;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}