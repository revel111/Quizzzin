package com.example.quizzzin.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * The Comment class represents a comment made by a user on a puzzle.
 * It contains information about the comment's text, the user who made it,
 * the puzzle it is associated with, and the date it was added.
 */
@Data
@Entity
@Table(name = "\"COMMENT\"")
public class Comment {

    /**
     * The unique identifier for the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The date and time when the comment was added.
     * This field is automatically set to the current date and time when the comment is created.
     */
    @Column(name = "date_of_adding", updatable = false)
    private LocalDateTime dateOfAdding;

    /**
     * The text content of the comment.
     * The text must be between 1 and 500 characters long.
     */
    @Size(min = 1, max = 500)
    @Column(name = "text")
    private String text;

    /**
     * The user who made the comment.
     * This field is eagerly fetched, meaning it is loaded immediately with the comment.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The puzzle that the comment is associated with.
     * This field is eagerly fetched, meaning it is loaded immediately with the comment.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "puzzle_id")
    private AbstractPuzzle puzzle;

    /**
     * Sets the dateOfAdding field to the current date and time before the comment is persisted.
     */
    @PrePersist
    protected void onCreate() {
        dateOfAdding = LocalDateTime.now();
    }
}