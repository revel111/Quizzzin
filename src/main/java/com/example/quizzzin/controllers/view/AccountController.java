package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The {@code AccountController} class handles HTTP requests related to user account and profile pages.
 * <p>
 * This controller provides methods to display user account details and user profiles based on the provided
 * authentication and path variable. It is responsible for rendering the appropriate views for these pages.
 * </p>
 */
@Controller
@AllArgsConstructor
public class AccountController {

    /**
     * Handles GET requests to the "/account" URL.
     * <p>
     * This method is used to render the user account page. It takes the currently authenticated user and
     * adds it to the model, which can be used to display user-specific information on the "user/account" page.
     * </p>
     *
     * @param user the currently authenticated user
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered (i.e., "user/account")
     */
    @GetMapping("/account")
    public String account(@AuthenticationPrincipal User user, Model model) {
        return "user/account";
    }

    /**
     * Handles GET requests to the "/profile/{id}" URL.
     * <p>
     * This method is used to render the profile page for a user with a specific ID. The user ID is extracted
     * from the URL path and can be used to retrieve and display user profile information on the "user/profile" page.
     * </p>
     *
     * @param id the ID of the user whose profile is to be displayed
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered (i.e., "user/profile")
     */
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model) {
        return "user/profile";
    }
}