package com.example.quizzzin.models.dto.get;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.LeaderboardDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ViewAbstractPuzzleDTO {
    private String title;
    private LocalDateTime dateOfAdding;
    private String description;
    private DifficultyType difficultyType;
    private double rating;
    private List<LeaderboardDTO> leaderboardDTOList;
}