package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.entities.UserPuzzleScore;
import com.example.quizzzin.repositories.UserPuzzleScoreRepository;
import com.example.quizzzin.services.UserPuzzleScoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {
    private final UserPuzzleScoreService userPuzzleScoreService;

    @GetMapping
    public String getHome(Model model) {
        model.addAttribute("leaderboard", userPuzzleScoreService.getGlobalLeaderBoard());
        return "home";
    }
}