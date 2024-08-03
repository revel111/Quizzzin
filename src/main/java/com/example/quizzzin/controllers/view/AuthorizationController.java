package com.example.quizzzin.controllers.view;

import com.example.quizzzin.configurations.AuthenticationService;
import com.example.quizzzin.configurations.CookieAuthenticationFilter;
import com.example.quizzzin.models.dto.other.RegisterUserDTO;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthorizationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

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
            return "user/register";

        User user = userService.saveUser(registerUserDTO);
        log.info("User was added: {}", user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "user/login";

        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@AuthenticationPrincipal User user,
                        HttpServletResponse response) {
        Cookie cookie = new Cookie(CookieAuthenticationFilter.COOKIE_NAME,
                authenticationService.generateToken(user));
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(Duration.of(1, ChronoUnit.DAYS).toSecondsPart());
        cookie.setPath("/");

        response.addCookie(cookie);
        return "redirect:/home";
    }
}