package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.dto.other.EmailDTO;
import com.example.quizzzin.models.dto.other.PasswordRecoveringDTO;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.services.EmailService;
import com.example.quizzzin.services.SecureTokenService;
import com.example.quizzzin.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/forgot-password")
@Slf4j
public class ResetPasswordController {
    private final UserService userService;
    private final EmailService emailService;
    private final SecureTokenService secureTokenService;

    @GetMapping
    public String getForgotPassword(Model model) {
        model.addAttribute("emailDTO", new EmailDTO());
        return "user/forgot-password";
    }

    @PostMapping
    public String restorePassword(@Valid EmailDTO emailDto,
                                  BindingResult bindingResult) {
        Optional<User> user = userService.findUserByEmail(emailDto.getEmail());

        if (user.isEmpty()) {
            bindingResult.rejectValue("email", "email.inappropriate", "User with such email wasn't find");
            return "user/forgot-password";
        }

        if (bindingResult.hasErrors())
            return "user/forgot-password";

        Map<String, Object> model = new HashMap<>() {
            {
                put("title", "Password recovering");
            }
        };
        emailService.sendEmail(user.get(), "/forgot-password/verify",
                "Password recovering", "emails/password-recovering", model);
        log.info("Email was sent to: {}", emailDto.getEmail());

        return "redirect:/login";
    }

    @GetMapping("/verify")
    public String verifyRestoring(@RequestParam String token,
                                  Model model) {
        boolean isVerified = secureTokenService.findByToken(token).isPresent();
        model.addAttribute("isVerified", isVerified);

        if (isVerified)
            model.addAllAttributes(new HashMap<>() {
                {
                    put("passwordRecoveringDTO", new PasswordRecoveringDTO());
                    put("token", token);
                }
            });

        return "user/recover-password";
    }

    @PostMapping("/verify")
    public ModelAndView verifyRestoring(@Valid @ModelAttribute("PasswordRecoveringDTO") PasswordRecoveringDTO passwordRecoveringDTO,
                                        BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");

        if (!passwordRecoveringDTO.getPassword()
                .equals(passwordRecoveringDTO.getPasswordConfirmation()))
            bindingResult.rejectValue("password", "passwords.different", "Passwords don't match");

        // ! due to redirect we can't see an error about unmatched passwords
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("token", passwordRecoveringDTO.getToken());
            return modelAndView;
        }

        log.info("User with email {} has changed password",
                secureTokenService.changePassword(passwordRecoveringDTO).getEmail());

        return modelAndView;
    }
}