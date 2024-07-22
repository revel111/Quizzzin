package com.example.quizzzin.models.dto.puzzles.get;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ViewAbstractPuzzleDTO {
    private Long id;
    private String title;
    private String dateOfAdding;
    private String description;
    private DifficultyType difficultyType;
    private double rating;
    private List<LeaderboardDTO> leaderboardDTOList;
    private List<ViewCommentDTO> commentDTOList;
}