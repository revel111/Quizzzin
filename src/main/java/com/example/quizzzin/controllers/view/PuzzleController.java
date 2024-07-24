package com.example.quizzzin.controllers.view;

import java.util.HashMap;
import java.util.Optional;

import com.example.quizzzin.models.dto.puzzles.solve.SolveRiddleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveWordleDTO;
import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.models.entities.Wordle;
import com.example.quizzzin.services.RiddleService;
import com.example.quizzzin.services.WordleService;
import com.example.quizzzin.utilities.TypeDefiner;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizzzin.models.dto.puzzles.get.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.services.AbstractPuzzleService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/puzzles")
@AllArgsConstructor
public class PuzzleController {
    private final AbstractPuzzleService abstractPuzzleService;
    private final RiddleService riddleService;
    private final WordleService wordleService;
    private final TypeDefiner typeDefiner;

    @GetMapping("/{id}")
    public String getPuzzle(Model model,
                            @PathVariable long id/*,
                            HttpSession session*/) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(id);
        if (abstractPuzzle.isEmpty())
            return "home";

        Long userId = null;
        User user;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userId = user.getId();
        } catch (ClassCastException ignored) {
        }

        Long finalUserId = userId;
        model.addAllAttributes(new HashMap<>() {
            {
                put("puzzleID", id);
                put("abstractPuzzleDTO", abstractPuzzleService.toViewAbstractPuzzleDTO(abstractPuzzle.get()));
                put("userId", finalUserId);
            }
        });

        return "puzzles/puzzle-view";
    }

    @GetMapping("/page/{pageNum}")
    public String getPuzzles(@PathVariable int pageNum,
                             @RequestParam(value = "sortBy", defaultValue = "rating") String sortBy,
                             @RequestParam(value = "dir", defaultValue = "asc") String dir,
                             Model model) {
        Page<AbstractPuzzle> pageAbstract = abstractPuzzleService.getPuzzlesPage(pageNum, sortBy, dir);

        if (pageAbstract == null)
            return "redirect:/puzzles/page/1";

        Page<FeedViewAbstractPuzzleDTO> page = abstractPuzzleService.toFeedViewPuzzlePage(pageAbstract);

        model.addAllAttributes(new HashMap<>() {
            {
                put("currentPage", pageNum);
                put("totalPages", page.getTotalPages());
                put("totalItems", page.getTotalElements());
                put("puzzles", page.getContent());
                put("sortField", sortBy);
                put("sortDirection", dir);
            }
        });

        return "puzzles/puzzle-feed";
    }

    @GetMapping("/solve")
    public String solveRiddle(Model model,
                              @RequestParam(name = "id") long id) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(id);
        if (abstractPuzzle.isEmpty())
            return "/home"; // ? or return 404

//        SolveRiddleDTO solveRiddleDTO = abstractPuzzleService.toSolveRiddleDTO(abstractPuzzle.get());
//        ViewAbstractPuzzleDTO abstractPuzzleDTO = abstractPuzzleService.toViewAbstractPuzzleDTO(abstractPuzzle.get());

        switch (typeDefiner.defineType(abstractPuzzle.get())) {
            case "Riddle" -> {
                Riddle riddle = (Riddle) abstractPuzzle.get();
                SolveRiddleDTO riddleDTO = riddleService.toSolveRiddleDTO(riddle);

                model.addAllAttributes(new HashMap<>() {
                    {
                        put("puzzleID", id);
                        put("solveRiddleDTO", riddleDTO);
                    }
                });
                return "puzzles/solve-riddle";
            }
            case "Wordle" -> {
                Wordle wordle = (Wordle) abstractPuzzle.get();
                SolveWordleDTO solveWordleDTO = wordleService.toSolveWordleDTO(wordle);

                model.addAllAttributes(new HashMap<>() {
                    {
                        put("puzzleID", id);
                        put("solveWordleDTO", solveWordleDTO);
                    }
                });
                return "puzzles/solve-wordle";
            }
        }

        return "puzzles/solve-riddle"; //TODO redirect somewhere else (404), but technically unreachable
    }
}