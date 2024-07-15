package com.example.quizzzin.models.dto.puzzles.add;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddRiddleDTO extends AddAbstractPuzzleDTO {
    @Size(min = 20, max = 250)
    @NotBlank
    private String text;
}