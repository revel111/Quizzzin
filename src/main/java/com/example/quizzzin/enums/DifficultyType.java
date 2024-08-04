package com.example.quizzzin.enums;

/**
 * The {@code DifficultyType} enum defines the various levels of difficulty that can be assigned
 * to puzzles in the application.
 * <p>
 * Each constant in this enum represents a different level of difficulty. These levels help categorize
 * puzzles based on their complexity, which can be used to filter or sort puzzles according to user preferences
 * or game requirements.
 * </p>
 * <p>
 * The levels are ordered from least to most challenging:
 * <ul>
 *     <li>{@link #EFFORTLESS} - The easiest level of difficulty, suitable for beginners.</li>
 *     <li>{@link #EASY} - Slightly more challenging than effortless, but still relatively simple.</li>
 *     <li>{@link #MEDIUM} - Represents a moderate level of difficulty, appropriate for average players.</li>
 *     <li>{@link #NORMAL} - A standard level of difficulty, balancing challenge and accessibility.</li>
 *     <li>{@link #HARD} - Designed for advanced players looking for a significant challenge.</li>
 *     <li>{@link #IMPOSSIBLE} - The most challenging level, intended for expert players who seek extreme difficulty.</li>
 * </ul>
 * </p>
 */
public enum DifficultyType {
    EFFORTLESS,
    EASY,
    MEDIUM,
    NORMAL,
    HARD,
    IMPOSSIBLE
}