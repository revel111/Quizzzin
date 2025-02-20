package com.example.quizzzin.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Riddle class represents a type of puzzle that contains a textual riddle.
 * This class extends the AbstractPuzzle class and adds an additional field for the riddle text.
 * It is mapped to the "RIDDLE" table in the database.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "\"RIDDLE\"")
public class Riddle extends AbstractPuzzle {

    /**
     * The text of the riddle.
     * This field must contain between 20 and 250 characters and cannot be blank.
     * It is mapped to the "text" column in the "RIDDLE" table.
     */
    @Column(name = "text")
    private String text;
}