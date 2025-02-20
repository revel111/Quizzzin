package com.example.quizzzin.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    @CreationTimestamp
    @Column(name = "date_of_adding", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime dateOfAdding;

    /**
     * The text content of the comment.
     * The text must be between 1 and 500 characters long.
     */
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
}