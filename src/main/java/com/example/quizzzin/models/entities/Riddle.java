package com.example.quizzzin.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "\"RIDDLE\"")
public class Riddle extends AbstractPuzzle {
    @Size(min = 20, max = 250)
    @Column(name = "text")
    private String text;
}