package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.dto.other.RegisterUserDTO;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthorizationController {
    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDTO", new RegisterUserDTO());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDTO") RegisterUserDTO registerUserDTO,
                           BindingResult bindingResult) {
        Optional<User> optionalUser = userService.findUserByEmail(registerUserDTO.getEmail());

        if (optionalUser.isPresent())
            bindingResult.rejectValue("email", "email.exists", "Email is already in use");

        if (bindingResult.hasErrors())
            return "redirect:/register";

        User user = userService.saveUser(registerUserDTO);
        log.info("User was added: {}", user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}