package com.example.quizzzin.models.dto.other;

import lombok.Data;

@Data
public class PasswordRecoveringDTO {
    //    @Pattern.List({
    //            @Pattern(regexp = ".*[0-9].*"),
    //            @Pattern(regexp = ".*[a-z].*"),
    //            @Pattern(regexp = ".*[A-Z].*"),
    //            @Pattern(regexp = ".*[@#$%^&+=!].*"),
    //            @Pattern(regexp = "\\S+"),
    //            @Pattern(regexp = ".{8,}")
    //    })
    private String password;
    //    @Pattern.List({
    //            @Pattern(regexp = ".*[0-9].*"),
    //            @Pattern(regexp = ".*[a-z].*"),
    //            @Pattern(regexp = ".*[A-Z].*"),
    //            @Pattern(regexp = ".*[@#$%^&+=!].*"),
    //            @Pattern(regexp = "\\S+"),
    //            @Pattern(regexp = ".{8,}")
    //    })
    private String passwordConfirmation;
    private String token;
}