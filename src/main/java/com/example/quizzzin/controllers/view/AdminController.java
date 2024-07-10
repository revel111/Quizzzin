package com.example.quizzzin.controllers.view;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.add.AddRiddleDTO;
import com.example.quizzzin.models.dto.add.AddWordleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.services.RiddleService;
import com.example.quizzzin.services.WordleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {
    private final RiddleService riddleService;
    private final WordleService wordleService;

    @GetMapping("/add-wordle")
    public String addWordle(Model model) {
        model.addAttribute("wordleDTO", new AddWordleDTO());
        model.addAttribute("difficulties", DifficultyType.values());
        return "admin/add-wordle";
    }

    @PostMapping("/add-wordle")
    public String addWordle(@Valid @ModelAttribute("wordleDTO") AddWordleDTO wordleDTO,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/admin/add-wordle";

        AbstractPuzzle wordle = wordleService.saveWordle(wordleDTO);
        log.info("Wordle was added: {}", wordle);

        return "redirect:/admin";
    }

    @GetMapping("/add-riddle")
    public String addRiddle(Model model) {
        model.addAttribute("riddleDTO", new AddRiddleDTO());
        model.addAttribute("difficulties", DifficultyType.values());
        return "admin/add-riddle";
    }

    @PostMapping("/add-riddle")
    public String addRiddle(@Valid @ModelAttribute("riddleDTO") AddRiddleDTO riddleDTO,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/admin/add-riddle";

        AbstractPuzzle riddle = riddleService.saveRiddle(riddleDTO);
        log.info("Riddle was added: {}", riddle);

        return "redirect:/admin";
    }

    @GetMapping
    public String getHome() {
        return "admin/home";
    }
}