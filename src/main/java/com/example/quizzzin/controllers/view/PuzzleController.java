package com.example.quizzzin.controllers.view;

import java.util.HashMap;
import java.util.Optional;

import com.example.quizzzin.models.dto.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.solve.SolveRiddleDTO;
import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.services.RiddleService;
import com.example.quizzzin.utilities.TypeDefiner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizzzin.models.dto.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.services.AbstractPuzzleService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/puzzles")
@AllArgsConstructor
public class PuzzleController {
    private final AbstractPuzzleService abstractPuzzleService;
    private final RiddleService riddleService;
    private final TypeDefiner typeDefiner;

    @GetMapping("/{id}")
    public String getPuzzle(Model model,
                            @PathVariable long id) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(id);
        if (abstractPuzzle.isEmpty())
            return "home";

        model.addAllAttributes(new HashMap<>() {
            {
                put("puzzleID", id);
                put("abstractPuzzleDTO", abstractPuzzleService.toViewAbstractPuzzleDTO(abstractPuzzle.get()));
            }
        });

        return "puzzles/puzzle-view";
    }

    @GetMapping("/page/{pageNum}")
    public String getPuzzles(@PathVariable int pageNum,
                             /*@RequestParam(value = "sortF", defaultValue = "difficultyType") String sortField,*/
                             /*@RequestParam(value = "sortDir", defaultValue = "asc") String sortDirection,*/
                             Model model) {
        Page<FeedViewAbstractPuzzleDTO> page = abstractPuzzleService.toFeedViewPuzzlePage(abstractPuzzleService.getPuzzlesPage(pageNum/*, sortField, sortDirection*/));

        model.addAllAttributes(new HashMap<>() {
            {
                put("currentPage", pageNum);
                put("totalPages", page.getTotalPages());
                put("totalItems", page.getTotalElements());
                put("puzzles", page.getContent());
//                put("sortField", sortField);
//                put("sortDirection", sortDirection);
//                put("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
            }
        });

        return "puzzles/puzzle-feed";
    }

    @GetMapping("/solve")
    public String solveRiddle(Model model,
                            @RequestParam(name="id") long id) {
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
            //TODO add implementation for the Wordle
        }

        return "puzzles/solve-riddle"; //TODO redirect somewhere else (404), but technically unreachable
    }
}