package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.dto.other.RegisterUserDTO;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.services.EmailService;
import com.example.quizzzin.services.SecureTokenService;
import com.example.quizzzin.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code AuthorizationController} class handles HTTP requests related to user registration and login.
 * <p>
 * This controller provides endpoints for user registration and authentication. It interacts with the {@link UserService}
 * </p>
 */
@Controller
@AllArgsConstructor
@Slf4j
public class AuthorizationController {
    private final UserService userService;
    private final SecureTokenService secureTokenService;
    private final EmailService emailService;
    //private final AuthenticationService authenticationService;

    /**
     * Displays the registration form for new users.
     * <p>
     * This method adds an empty {@link RegisterUserDTO} object to the model, which is used to populate the registration
     * form on the "user/register" page.
     * </p>
     *
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered (i.e., "user/register")
     */
    @GetMapping("/register")
    public String register(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAllAttributes(new HashMap<>() {
                {
                    put("userDTO", new RegisterUserDTO());
                    put("maxDate", LocalDate.now().minusYears(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
            });
            return "user/register";
        }

        return "redirect:/home";
    }

    /**
     * Handles the form submission for user registration.
     * <p>
     * This method performs validation on the provided {@link RegisterUserDTO} object. If the email is already in use or
     * if there are other validation errors, it redisplay the form with error messages. If validation passes, the user
     * is saved using {@link UserService}, and the user is redirected to the login page.
     * </p>
     *
     * @param registerUserDTO the {@link RegisterUserDTO} object containing user registration details
     * @param bindingResult   the {@link BindingResult} object used to store validation errors
     * @return the name of the view to be rendered or a redirect URL based on validation results
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDTO") RegisterUserDTO registerUserDTO,
                           BindingResult bindingResult,
                           RedirectAttributes attributes) {
        Optional<User> optionalUser = userService.findUserByEmail(registerUserDTO.getEmail());

        if (optionalUser.isPresent())
            bindingResult.rejectValue("email", "email.exists", "Email is already in use");

        if (!registerUserDTO.getPassword()
                .equals(registerUserDTO.getConfirmationPassword()))
            bindingResult.rejectValue("password", "passwords.different", "Passwords don't match");

        if (bindingResult.hasErrors())
            return "user/register";

        User user = userService.registerUser(registerUserDTO);
        log.info("User was added: {}", user);

        return sendVerificationMail(user, attributes);
    }

    @PostMapping("/resend-verification-email")
    public String resendEmail(@RequestParam String email,
                              RedirectAttributes attributes) {
        Optional<User> optionalUser = userService.findUserByEmail(email);

        if (optionalUser.isEmpty()) {
            attributes.addFlashAttribute("errorMessage", "No user found with this email.");
            return "redirect:/register";
        }

        return sendVerificationMail(optionalUser.get(), attributes);
    }

    @GetMapping("/verify-account")
    public String verify(@RequestParam String token,
                         Model model) {
        model.addAttribute("isVerified", secureTokenService.verifyToken(token));
        return "user/verify-account";
    }

    /**
     * Displays the login page.
     * <p>
     * If the user is already authenticated, they are redirected to the home page. Otherwise, the login page is rendered.
     * </p>
     *
     * @return the name of the view to be rendered or a redirect URL based on the user's authentication status
     */
    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "user/login";

        return "redirect:/home";
    }

    private String sendVerificationMail(User user,
                                        RedirectAttributes attributes) {
        Map<String, Object> model = new HashMap<>() {
            {
                put("title", "Account verification");
            }
        };
        emailService.sendEmail(user, "/verify-account",
                "Account verification", "emails/email-verification", model);
        log.info("Email was sent to: {}", user.getEmail());

        attributes.addFlashAttribute("message", "The email was sent.");
        attributes.addFlashAttribute("email", user.getEmail());

        return "redirect:/register";
    }
}