package com.example.quizzzin.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Wordle class represents a specific type of puzzle that extends the {@link AbstractPuzzle} class.
 * This class maps to the "WORDLE" table in the database.
 * <p>
 * As a subclass of {@link AbstractPuzzle}, Wordle inherits all the attributes and relationships
 * defined in the {@link AbstractPuzzle} class, including properties such as title, date of adding,
 * description, answer, difficulty, puzzle scores, puzzle ratings, and comments.
 * </p>
 * <p>
 * This class does not introduce additional fields or methods beyond those inherited from {@link AbstractPuzzle}.
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "\"WORDLE\"")
public class Wordle extends AbstractPuzzle {
}