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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                                  BindingResult bindingResult,
                                  RedirectAttributes attributes) {
        Optional<User> optionalUser = userService.findUserByEmail(emailDto.getEmail());

        if (optionalUser.isEmpty())
            bindingResult.rejectValue("email", "email.inappropriate", "User with such email wasn't find");

        if (bindingResult.hasErrors())
            return "user/forgot-password";

        return sendVerificationMail(optionalUser.get(), attributes);
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
    public String verifyRestoring(@Valid @ModelAttribute("PasswordRecoveringDTO") PasswordRecoveringDTO passwordRecoveringDTO,
                                  BindingResult bindingResult,
                                  Model model) {
        if (!passwordRecoveringDTO.getPassword()
                .equals(passwordRecoveringDTO.getPasswordConfirmation()))
            bindingResult.rejectValue("password", "passwords.different", "Passwords don't match");

        if (bindingResult.hasErrors()) {
            model.addAttribute("token", passwordRecoveringDTO.getToken());
            return "user/recover-password";
        }

        log.info("User with email {} has changed password",
                secureTokenService.changePassword(passwordRecoveringDTO).getEmail());

        return "redirect:/login";
    }

    @PostMapping("/resend-verification-email")
    public String verifyRestoring(@RequestParam String email,
                                  RedirectAttributes attributes) {
        Optional<User> optionalUser = userService.findUserByEmail(email);

        if (optionalUser.isEmpty()) {
            attributes.addFlashAttribute("errorMessage", "No user found with this email.");
            return "redirect:/register";
        }

        return sendVerificationMail(optionalUser.get(), attributes);
    }

    private String sendVerificationMail(User user,
                                        RedirectAttributes attributes) {
        Map<String, Object> emailModel = new HashMap<>() {
            {
                put("title", "Password recovering");
            }
        };
        emailService.sendEmail(user, "/forgot-password/verify",
                "Password recovering", "emails/password-recovering", emailModel);
        log.info("Email was sent to: {}", user.getEmail());

        attributes.addFlashAttribute("message", "The email was sent.");
        attributes.addFlashAttribute("email", user.getEmail());

        return "redirect:/login";
    }
}