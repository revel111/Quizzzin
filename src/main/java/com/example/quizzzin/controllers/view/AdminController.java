package com.example.quizzzin.controllers.view;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.puzzles.add.AddRiddleDTO;
import com.example.quizzzin.models.dto.puzzles.add.AddWordleDTO;
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

/**
 * The {@code AdminController} class handles HTTP requests related to administrative tasks, such as adding new puzzles
 * and viewing the admin dashboard.
 * <p>
 * This controller provides methods for adding new Wordles and Riddles, as well as rendering the admin home page.
 * It uses services for interacting with the puzzle data and includes validation for user input.
 * </p>
 */
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {
    private final RiddleService riddleService;
    private final WordleService wordleService;

    /**
     * Displays the admin home page.
     * <p>
     * This method renders the "admin/home" view, which serves as the main dashboard for administrators.
     * </p>
     *
     * @return the name of the view to be rendered (i.e., "admin/home")
     */
    @GetMapping
    public String getHome() {
        return "admin/home";
    }

    /**
     * Displays the form for adding a new Wordle.
     * <p>
     * This method adds an empty {@link AddWordleDTO} object and a list of {@link DifficultyType} values to the model,
     * which are then used to populate the form fields and dropdowns on the "admin/add-wordle" page.
     * </p>
     *
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered (i.e., "admin/add-wordle")
     */
    @GetMapping("/add-wordle")
    public String addWordle(Model model) {
        model.addAttribute("wordleDTO", new AddWordleDTO());
        model.addAttribute("difficulties", DifficultyType.values());

        return "admin/add-wordle";
    }

    /**
     * Handles the submission of the form for adding a new Wordle.
     * <p>
     * This method performs validation on the provided {@link AddWordleDTO} object, checking if the Wordle already exists
     * or if the answer is inappropriate. If validation errors occur, it redisplays the form with error messages.
     * If validation passes, the Wordle is saved using {@link WordleService}, and the user is redirected to the admin home page.
     * </p>
     *
     * @param wordleDTO     the {@link AddWordleDTO} object containing details of the Wordle to be added
     * @param bindingResult the {@link BindingResult} object used to store validation errors
     * @param model         the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered or a redirect URL based on validation results
     */
    @PostMapping("/add-wordle")
    public String addWordle(@Valid @ModelAttribute("wordleDTO") AddWordleDTO wordleDTO,
                            BindingResult bindingResult,
                            Model model) {
        String answer = wordleDTO.getAnswer().toLowerCase();

        if (wordleService.findWordleByAnswer(answer).isPresent())
            bindingResult.rejectValue("answer", "wordle.exists", "Wordle with such answer exists");

        if (!wordleService.checkWordInAPIDictionary(answer))
            bindingResult.rejectValue("answer", "answer.inappropriate", "This word is inappropriate");

        if (bindingResult.hasErrors()) {
            model.addAttribute("difficulties", DifficultyType.values());
            return "admin/add-wordle";
        }

        AbstractPuzzle wordle = wordleService.saveWordle(wordleDTO);
        log.info("Wordle was added: {}", wordle);

        return "redirect:/admin";
    }

    /**
     * Displays the form for adding a new Riddle.
     * <p>
     * This method adds an empty {@link AddRiddleDTO} object and a list of {@link DifficultyType} values to the model,
     * which are then used to populate the form fields and dropdowns on the "admin/add-riddle" page.
     * </p>
     *
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered (i.e., "admin/add-riddle")
     */
    @GetMapping("/add-riddle")
    public String addRiddle(Model model) {
        model.addAttribute("riddleDTO", new AddRiddleDTO());
        model.addAttribute("difficulties", DifficultyType.values());

        return "admin/add-riddle";
    }

    /**
     * Handles the submission of the form for adding a new Riddle.
     * <p>
     * This method performs validation on the provided {@link AddRiddleDTO} object. If validation errors occur, it redisplays
     * the form with error messages. If validation passes, the Riddle is saved using {@link RiddleService}, and the user
     * is redirected to the admin home page.
     * </p>
     *
     * @param riddleDTO     the {@link AddRiddleDTO} object containing details of the Riddle to be added
     * @param bindingResult the {@link BindingResult} object used to store validation errors
     * @return the name of the view to be rendered or a redirect URL based on validation results
     */
    @PostMapping("/add-riddle")
    public String addRiddle(@Valid @ModelAttribute("riddleDTO") AddRiddleDTO riddleDTO,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/add-riddle";

        AbstractPuzzle riddle = riddleService.saveRiddle(riddleDTO);
        log.info("Riddle was added: {}", riddle);

        return "redirect:/admin";
    }
}