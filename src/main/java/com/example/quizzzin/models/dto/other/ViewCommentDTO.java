package com.example.quizzzin.models.dto.other;

import java.time.LocalDateTime;

public record ViewCommentDTO(Long id, Long userId, String username, String dateOfAdding, String text) {}