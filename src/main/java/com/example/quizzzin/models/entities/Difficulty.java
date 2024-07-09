package com.example.quizzzin.models.entities;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "\"DIFFICULTY\"")
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private DifficultyType name;
    @OneToMany(mappedBy = "difficulty")
    private Set<AbstractPuzzle> puzzles;
}