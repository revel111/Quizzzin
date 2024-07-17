package com.example.quizzzin.controllers.view;

import com.example.quizzzin.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@AllArgsConstructor
public class GlobalController {
    private final UserService userService;

    @ModelAttribute("username")
    public String getUsername() {
        return userService.getAuthenticatedUsername();
    }
}