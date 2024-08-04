package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * The AbstractPuzzle class serves as a base class for various types of puzzles in the application.
 * It defines common attributes and relationships that are shared among different types of puzzles.
 * This class uses JPA annotations to map its fields to database columns and establish relationships
 * with other entities.
 * <p>
 * This class is abstract and marked as an entity, meaning it will be mapped to a database table,
 * and other classes can extend it to inherit its properties and behavior.
 */
@Data
@Entity
@Table(name = "\"PUZZLE\"")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractPuzzle {

    /**
     * The unique identifier for the puzzle.
     * This field is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The title of the puzzle.
     * The title must be between 5 and 20 characters long and cannot be blank.
     */
    @Size(min = 5, max = 20)
    @NotBlank
    @Column(name = "title")
    private String title;

    /**
     * The date and time when the puzzle was added.
     * This field is automatically set to the current date and time when the puzzle is created and is not updatable.
     */
    @Column(name = "date_of_adding", updatable = false)
    private LocalDateTime dateOfAdding;

    /**
     * A brief description of the puzzle.
     * The description must be between 5 and 50 characters long and cannot be blank.
     */
    @Size(min = 5, max = 50)
    @NotBlank
    @Column(name = "description")
    private String description;

    /**
     * The answer to the puzzle.
     * The answer cannot be blank.
     */
    @Column(name = "answer")
    @NotBlank
    private String answer;

    /**
     * The difficulty level of the puzzle.
     * This is a many-to-one relationship with the Difficulty entity.
     */
    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    /**
     * The scores given to this puzzle by users.
     * This is a one-to-many relationship with the UserPuzzleScore entity.
     */
    @OneToMany(mappedBy = "puzzle")
    private Set<UserPuzzleScore> puzzleScores;

    /**
     * The ratings given to this puzzle by users.
     * This is a one-to-many relationship with the UserPuzzleRating entity.
     */
    @OneToMany(mappedBy = "puzzle")
    private Set<UserPuzzleRating> puzzleRatings;

    /**
     * The comments made on this puzzle by users.
     * This is a one-to-many relationship with the Comment entity and is eagerly fetched.
     */
    @OneToMany(mappedBy = "puzzle", fetch = FetchType.EAGER)
    private Set<Comment> comments;

    /**
     * Sets the date of adding to the current time before persisting the entity.
     */
    @PrePersist
    protected void onCreate() {
        dateOfAdding = LocalDateTime.now();
    }
}