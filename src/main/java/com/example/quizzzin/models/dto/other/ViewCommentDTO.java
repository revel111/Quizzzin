package com.example.quizzzin.models.dto.other;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewCommentDTO {
    private String username;
    private LocalDateTime dateOfAdding;
    private String text;
}
