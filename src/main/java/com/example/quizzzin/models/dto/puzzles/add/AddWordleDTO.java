package com.example.quizzzin.models.dto.puzzles.add;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The {@code AddWordleDTO} class extends {@code AddAbstractPuzzleDTO} and is used for creating
 * or updating a Wordle puzzle. It inherits all attributes from {@code AddAbstractPuzzleDTO} and
 * does not add any additional fields specific to Wordle puzzles.
 * <p>
 * This class provides the structure for DTOs used when adding or updating Wordle puzzles,
 * including essential attributes such as title, description, answer, and difficulty.
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddWordleDTO extends AddAbstractPuzzleDTO {
}