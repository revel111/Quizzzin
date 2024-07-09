package com.example.quizzzin.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RiddleDTO extends AbstractPuzzleDTO {
    @Size(min = 20, max = 250)
    @NotBlank
    private String text;
}