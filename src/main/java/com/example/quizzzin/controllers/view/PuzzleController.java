package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.dto.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.services.AbstractPuzzleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/puzzles")
@AllArgsConstructor
public class PuzzleController {
    private final AbstractPuzzleService abstractPuzzleService;

    @GetMapping("/{id}")
    public String getPuzzle(Model model, @PathVariable long id) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(id);
        if (abstractPuzzle.isEmpty())
            return "home";

        model.addAttribute("abstractPuzzleDTO", abstractPuzzleService.fabricateViewAbstractPuzzleDTO(abstractPuzzle.get()));

        return "puzzles/puzzle-view";
    }

    @GetMapping
    public String getPuzzles(Model model) {
//        model.addAttribute("puzzles", abstractPuzzleService.getPuzzlesPage());
        return "puzzles/puzzle-feed";
    }
}