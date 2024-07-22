package com.example.quizzzin.models.entities;

import java.util.Set;

import com.example.quizzzin.enums.DifficultyType;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "\"DIFFICULTY\"")
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "name")
    private DifficultyType name;
    @OneToMany(mappedBy = "difficulty")
    private Set<AbstractPuzzle> puzzles;
}