package com.example.quizzzin.models.dto.solve;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SolveRiddleDTO extends SolveAbstractPuzzleDTO {
    private String text;
}
