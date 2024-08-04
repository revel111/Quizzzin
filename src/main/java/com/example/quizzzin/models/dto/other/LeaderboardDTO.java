package com.example.quizzzin.models.dto.other;

/**
 * The {@code LeaderboardDTO} record represents a data transfer object (DTO) used for displaying
 * leaderboard information. It encapsulates the user ID, nickname, and the total score of a user,
 * which are essential for presenting the leaderboard rankings in the application.
 * <p>
 * This record is used to transfer data related to user scores and rankings in a leaderboard context.
 * </p>
 *
 * @param userId   The unique identifier of the user. This field is used to uniquely identify the user.
 * @param nickname The nickname of the user. This field represents the user's chosen display name.
 * @param score    The total score accumulated by the user. This field represents the user's performance in the puzzles.
 */
public record LeaderboardDTO(Long userId, String nickname, long score) {
}