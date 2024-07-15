package com.example.quizzzin.services;

import com.example.quizzzin.enums.RoleType;
import com.example.quizzzin.mappers.UserMapper;
import com.example.quizzzin.models.dto.other.RegisterUserDTO;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

//    public boolean loginUser(LoginUserDTO loginUserDTO) {
//        Optional<User> user = userRepository.findByEmail(loginUserDTO.getEmail());
//        return user.filter(value -> passwordEncoder.matches(loginUserDTO.getPassword(), value.getPassword())).isPresent();
////        return userRepository.findByEmailAndPassword(loginUserDTO.getEmail(), passwordEncoder.encode(log  inUserDTO.getPassword()));
//    }
}