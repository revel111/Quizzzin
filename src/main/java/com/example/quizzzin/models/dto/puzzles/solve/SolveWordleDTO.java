package com.example.quizzzin.models.dto.puzzles.solve;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The {@code SolveWordleDTO} class extends {@code SolveAbstractPuzzleDTO} and is used
 * for transferring data related to solving a Wordle puzzle. It inherits all fields from
 * {@code SolveAbstractPuzzleDTO} and does not add any additional fields or methods.
 * <p>
 * This class is specifically tailored for Wordle puzzles, which are a type of puzzle
 * with unique rules and characteristics. By extending {@code SolveAbstractPuzzleDTO},
 * {@code SolveWordleDTO} retains the basic attributes necessary for solving the puzzle, such
 * as the puzzle ID, title, description, and difficulty type.
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SolveWordleDTO extends SolveAbstractPuzzleDTO {
}