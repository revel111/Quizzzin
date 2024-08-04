package com.example.quizzzin.models.entities;

import java.util.Set;

import com.example.quizzzin.enums.DifficultyType;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The Difficulty class represents the difficulty level of a puzzle.
 * This class is mapped to the "DIFFICULTY" table in the database.
 * It includes fields for the difficulty ID, name, and the set of puzzles associated with this difficulty level.
 */
@Data
@Entity
@Table(name = "\"DIFFICULTY\"")
public class Difficulty {

    /**
     * The unique identifier for the difficulty.
     * This field is auto-generated and serves as the primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the difficulty level.
     * This field uses an enumerated type and is mapped to the "name" column in the "DIFFICULTY" table.
     * The ordinal value of the enumeration is stored in the database.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "name")
    private DifficultyType name;

    /**
     * The set of puzzles associated with this difficulty level.
     * This field is mapped by the "difficulty" field in the AbstractPuzzle entity.
     */
    @OneToMany(mappedBy = "difficulty")
    private Set<AbstractPuzzle> puzzles;
}