package com.example.quizzzin.controllers.view;

import java.util.HashMap;
import java.util.Optional;

import com.example.quizzzin.models.dto.other.RatePuzzleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveRiddleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveWordleDTO;
import com.example.quizzzin.models.entities.*;
import com.example.quizzzin.services.*;
import com.example.quizzzin.utilities.TypeDefiner;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.quizzzin.models.dto.puzzles.get.FeedViewAbstractPuzzleDTO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/puzzles")
@AllArgsConstructor
public class PuzzleController {
    private final AbstractPuzzleService abstractPuzzleService;
    private final UserService userService;
    private final UserPuzzleRatingService userPuzzleRatingService;
    private final RiddleService riddleService;
    private final WordleService wordleService;
    private final UserPuzzleScoreService userPuzzleScoreService;
    private final TypeDefiner typeDefiner;

    @GetMapping("/{id}")
    public String getPuzzle(Model model,
                            @PathVariable long id) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(id);
        if (abstractPuzzle.isEmpty())
            return "home";

        Long userId = userService.getAuthenticatedUserId();

        model.addAllAttributes(new HashMap<>() {
            {
                put("puzzleID", id);
                put("abstractPuzzleDTO", abstractPuzzleService.toViewAbstractPuzzleDTO(abstractPuzzle.get()));
                put("userId", userId);
                put("isSolved", userPuzzleScoreService.findByPuzzleIdAndUserId(id, userId).isPresent());
                put("isRated", userPuzzleRatingService.findByPuzzleIdAndUserId(id, userId).isPresent());
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
            return "home"; // TODO return 404

        model.addAllAttributes(new HashMap<>() {
            {
                put("puzzleID", id);
                put("userID", userService.getAuthenticatedUserId());
            }
        });

        switch (typeDefiner.defineType(abstractPuzzle.get())) {
            case "Riddle" -> {
                Riddle riddle = (Riddle) abstractPuzzle.get();
                SolveRiddleDTO riddleDTO = riddleService.toSolveRiddleDTO(riddle);


                model.addAttribute("solveRiddleDTO", riddleDTO);
//                model.addAllAttributes(new HashMap<>() {
//                    {
//                        put("solveRiddleDTO", riddleDTO);
//                    }
//                });
                return "puzzles/solve-riddle";
            }
            case "Wordle" -> {
                Wordle wordle = (Wordle) abstractPuzzle.get();
                SolveWordleDTO solveWordleDTO = wordleService.toSolveWordleDTO(wordle);

                // ! Checking whether the API works
                if (!wordleService.checkWordInAPIDictionary("word"))
                    return "redirect:home"; // TODO Redirect to some page with explanation why user can't solve a wordle.

                model.addAttribute("solveWordleDTO", solveWordleDTO);
//                model.addAllAttributes(new HashMap<>() {
//                    {
//                        put("solveWordleDTO", solveWordleDTO);
//                    }
//                });
                return "puzzles/solve-wordle";
            }
        }

        return "puzzles/solve-riddle"; // TODO return 404
    }

    @PostMapping("/{id}/rate")
    public String rateRiddle(@PathVariable long id,
                             @RequestParam("rating") int rating) {
        userPuzzleRatingService.save(new RatePuzzleDTO(id, rating));

        return "redirect:/home";
    }
}