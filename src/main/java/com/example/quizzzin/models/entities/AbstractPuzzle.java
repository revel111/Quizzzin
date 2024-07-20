package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "\"PUZZLE\"")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractPuzzle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(min = 5, max = 20)
    @NotBlank
    @Column(name = "title")
    private String title;
    @Column(name = "date_of_adding", updatable = false)
    private LocalDateTime dateOfAdding;
    @Size(min = 5, max = 50)
    @NotBlank
    @Column(name = "description")
    private String description;
    @Column(name = "answer")
    @NotBlank
    private String answer;
    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;
    @OneToMany(mappedBy = "puzzle")
    private Set<UserPuzzleScore> puzzleScores;
    @OneToMany(mappedBy = "puzzle")
    private Set<UserPuzzleRating> puzzleRatings;
    @OneToMany(mappedBy = "puzzle", fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @PrePersist
    protected void onCreate() {
        dateOfAdding = LocalDateTime.now();
    }
}