package com.example.quizzzin.controllers.view;

import com.example.quizzzin.models.dto.other.RatePuzzleDTO;
import com.example.quizzzin.models.dto.puzzles.get.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveRiddleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveWordleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.models.entities.Wordle;
import com.example.quizzzin.services.AbstractPuzzleService;
import com.example.quizzzin.services.CommentService;
import com.example.quizzzin.services.RiddleService;
import com.example.quizzzin.services.UserPuzzleRatingService;
import com.example.quizzzin.services.UserPuzzleScoreService;
import com.example.quizzzin.services.UserService;
import com.example.quizzzin.services.WordleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * The {@code PuzzleController} class handles HTTP requests related to puzzles in the application.
 * <p>
 * It manages actions such as viewing puzzles, solving them, and rating them. The controller interacts with
 * services such as {@link AbstractPuzzleService}, {@link UserService}, {@link UserPuzzleRatingService},
 * {@link RiddleService}, {@link WordleService}, and {@link UserPuzzleScoreService} to perform these actions.
 * </p>
 */
@Controller
@RequestMapping("/puzzles")
@Slf4j
@AllArgsConstructor
public class PuzzleController {
    private final AbstractPuzzleService abstractPuzzleService;
    private final UserService userService;
    private final UserPuzzleRatingService userPuzzleRatingService;
    private final RiddleService riddleService;
    private final WordleService wordleService;
    private final UserPuzzleScoreService userPuzzleScoreService;
    private final CommentService commentService;

    /**
     * Handles the HTTP GET request to view a specific puzzle.
     * <p>
     * This method retrieves the puzzle by its ID and adds relevant attributes to the model, such as puzzle details,
     * whether the puzzle has been solved or rated by the current user, etc. If the puzzle is not found, it redirects
     * to the home page.
     * </p>
     *
     * @param model the {@link Model} object used to pass data to the view
     * @param id    the ID of the puzzle to be retrieved
     * @return the name of the view to be rendered (i.e., "puzzles/puzzle-view")
     */
    @GetMapping("/{id}")
    public String getPuzzle(Model model,
                            @PathVariable long id) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(id);

        if (abstractPuzzle.isEmpty())
            return "redirect:/home";

        Long userId = userService.getAuthenticatedUserId();

        model.addAllAttributes(new HashMap<>() {
            {
                put("puzzleID", id);
                put("abstractPuzzleDTO", abstractPuzzleService.toViewAbstractPuzzleDTO(abstractPuzzle.get()));
                put("comments", commentService.mapComments(abstractPuzzle.get().getComments()));
                put("scores", userPuzzleScoreService.mapUsersScores(abstractPuzzle.get().getPuzzleScores()));
                put("userId", userId);
                put("isSolved", userPuzzleScoreService.findByPuzzleIdAndUserId(id, userId).isPresent());
                put("isRated", userPuzzleRatingService.findByPuzzleIdAndUserId(id, userId).isPresent());
            }
        });

        return "puzzles/puzzle-view";
    }

    /**
     * Handles the HTTP GET request to view a page of puzzles.
     * <p>
     * This method retrieves a paginated list of puzzles, sorted according to the specified criteria (e.g., rating),
     * and adds the relevant pagination and sorting information to the model.
     * </p>
     *
     * @param pageNum the page number to retrieve
     * @param sortBy  the field by which to sort the puzzles
     * @param dir     the direction to sort the puzzles (ascending or descending)
     * @param model   the {@link Model} object used to pass data to the view
     * @return the name of the view to be rendered (i.e., "puzzles/puzzle-feed")
     */
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

    /**
     * Handles the HTTP GET request to display a puzzle-solving page.
     * <p>
     * This method determines the type of puzzle (Riddle or Wordle) and retrieves the necessary data to display the
     * puzzle-solving interface. If the puzzle is not found or is not solvable, appropriate actions are taken,
     * such as redirecting to the home page.
     * </p>
     *
     * @param user  the currently authenticated user
     * @param model the {@link Model} object used to pass data to the view
     * @param id    the ID of the puzzle to be solved
     * @return the name of the view to be rendered (i.e., "puzzles/solve-riddle" or "puzzles/solve-wordle")
     */
    @GetMapping("/{id}/solve")
    public String solveRiddle(@AuthenticationPrincipal User user,
                              Model model,
                              @PathVariable long id) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(id);

        if (abstractPuzzle.isEmpty())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        long userId = user.getId();

        if (userPuzzleScoreService.findByPuzzleIdAndUserId(id, userId).isPresent())
            return "redirect:/home";

        model.addAllAttributes(new HashMap<>() {
            {
                put("puzzleID", id);
                put("userID", userId);
            }
        });

        switch (abstractPuzzle.get()) {
            case Riddle riddle -> {
                SolveRiddleDTO riddleDTO = riddleService.toSolveRiddleDTO(riddle);

                model.addAttribute("solveRiddleDTO", riddleDTO);

                return "puzzles/solve-riddle";
            }
            case Wordle wordle -> {
                SolveWordleDTO solveWordleDTO = wordleService.toSolveWordleDTO(wordle);

                // ! Checking whether the API works
                if (!wordleService.checkWordInAPIDictionary("word"))
                    return "redirect:/home"; // TODO Redirect to some page with explanation why user can't solve a wordle.

                model.addAttribute("solveWordleDTO", solveWordleDTO);

                return "puzzles/solve-wordle";
            }
            default -> throw new IllegalStateException("Unexpected value: " + abstractPuzzle.get());
        }
    }

    /**
     * Handles the HTTP POST request to rate a puzzle.
     * <p>
     * This method saves the rating for the specified puzzle and then redirects the user to the home page.
     * </p>
     *
     * @param id     the ID of the puzzle to be rated
     * @param rating the rating value provided by the user
     * @return the name of the view to be redirected to (i.e., "redirect:/home")
     */
    @PostMapping("/{id}/rate")
    public String rateRiddle(@PathVariable long id,
                             @RequestParam("rating") int rating) {
        userPuzzleRatingService.save(new RatePuzzleDTO(id, rating));
        log.info("Rating for puzzle with puzzle id {} and user id {} was saved", id, rating);

        return "redirect:/home";
    }
}