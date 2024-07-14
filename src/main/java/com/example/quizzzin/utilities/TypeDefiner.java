package com.example.quizzzin.utilities;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import org.springframework.stereotype.Service;

@Service
public class TypeDefiner {
    public String defineType(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzle.getClass().getSimpleName();
    }
}