package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"COMMENT\"")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_of_adding", updatable = false)
    private LocalDateTime dateOfAdding;
    @Size(min = 1, max = 500)
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "puzzle_id")
    private AbstractPuzzle puzzle;
}