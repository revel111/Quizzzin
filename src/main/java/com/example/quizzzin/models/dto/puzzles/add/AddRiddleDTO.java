package com.example.quizzzin.models.dto.puzzles.add;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The {@code AddRiddleDTO} class extends {@code AddAbstractPuzzleDTO} and is used for creating
 * or updating a riddle puzzle. It includes additional attributes specific to riddles, such as
 * the text of the riddle.
 * <p>
 * This class provides the structure for DTOs used when adding or updating riddles, including
 * essential attributes inherited from {@code AddAbstractPuzzleDTO} and additional attributes
 * specific to riddles.
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddRiddleDTO extends AddAbstractPuzzleDTO {

    /**
     * The text of the riddle.
     * <p>
     * The text must be between 20 and 250 characters in length and cannot be blank.
     * </p>
     */
    @Size(min = 20, max = 250)
    @NotBlank
    private String text;
}
