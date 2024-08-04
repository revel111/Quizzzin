package com.example.quizzzin.controllers.view;

import com.example.quizzzin.services.UserPuzzleScoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The {@code HomeController} class handles HTTP requests for the home page of the application.
 * <p>
 * This controller provides an endpoint to render the home page, which includes the global leaderboard
 * for the application. It interacts with {@link UserPuzzleScoreService} to fetch leaderboard data.
 * </p>
 */
@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {
    private final UserPuzzleScoreService userPuzzleScoreService;

    /**
     * Handles the HTTP GET request to display the home page.
     * <p>
     * This method retrieves the global leaderboard data from {@link UserPuzzleScoreService} and adds it to the
     * model. The model data is then used to populate the home page view ("home").
     * </p>
     *
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered (i.e., "home")
     */
    @GetMapping
    public String getHome(Model model) {
        model.addAttribute("leaderboard", userPuzzleScoreService.getGlobalLeaderBoard());
        return "home";
    }
}