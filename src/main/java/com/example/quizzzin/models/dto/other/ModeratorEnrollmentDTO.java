package com.example.quizzzin.models.dto.other;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ModeratorEnrollmentDTO {
    @AssertTrue(groups = StepOne.class)
    private boolean agreed;

    @Size(min = 50, max = 500, groups = StepTwo.class)
    @NotBlank(groups = StepTwo.class)
    @NotEmpty(groups = StepTwo.class)
    private String text;

    @NotNull(groups = StepTwo.class)
    private String workHours;

    private boolean stepOneCompleted;

    public interface StepOne{}
    public interface StepTwo{}
}