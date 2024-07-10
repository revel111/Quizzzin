package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.dto.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.services.AbstractPuzzleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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

        model.addAttribute("abstractPuzzleDTO", abstractPuzzleService.toViewAbstractPuzzleDTO(abstractPuzzle.get()));

        return "puzzles/puzzle-view";
    }

    @GetMapping("/page/{pageNum}")
    public String getPuzzles(@PathVariable int pageNum, Model model) {
        Page<FeedViewAbstractPuzzleDTO> page = abstractPuzzleService.toFeedViewPuzzlePage(abstractPuzzleService.getPuzzlesPage(pageNum));

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("puzzles", page.getContent());

        return "puzzles/puzzle-feed";
    }
}