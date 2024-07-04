package com.example.quizzzin.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "\"WORDLE\"")
public class Wordle extends AbstractPuzzle {

}
