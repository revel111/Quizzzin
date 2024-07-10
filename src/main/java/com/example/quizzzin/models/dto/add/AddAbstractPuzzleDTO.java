package com.example.quizzzin.models.dto.add;

import com.example.quizzzin.enums.DifficultyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class AddAbstractPuzzleDTO {
    @Size(min = 5, max = 20)
    @NotBlank
    private String title;
    @Size(min = 5, max = 50)
    @NotBlank
    private String description;
    @NotBlank
    private String answer;
    private DifficultyType difficultyType;
}