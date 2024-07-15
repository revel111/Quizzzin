package com.example.quizzzin.models.dto.other;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterUserDTO {
    @Size(min = 2, max = 50)
    @NotBlank
    private String name;
    @Size(min = 2, max = 50)
    @NotBlank
    private String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    @Size(min = 1, max = 25)
    @NotBlank
    private String nickname;
    @Email
    private String email;
//    @Pattern.List({
//            @Pattern(regexp = ".*[0-9].*"),
//            @Pattern(regexp = ".*[a-z].*"),
//            @Pattern(regexp = ".*[A-Z].*"),
//            @Pattern(regexp = ".*[@#$%^&+=!].*"),
//            @Pattern(regexp = "\\S+"),
//            @Pattern(regexp = ".{8,}")
//    })
    private String password;
}