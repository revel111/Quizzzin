package com.example.quizzzin.models.dto.other;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailDTO {
    @Email
    private String email;
}