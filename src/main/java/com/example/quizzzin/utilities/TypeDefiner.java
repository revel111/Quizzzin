package com.example.quizzzin.utilities;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import org.springframework.stereotype.Service;


/**
 * The TypeDefiner class provides functionality to determine the class type of a given AbstractPuzzle entity.
 */
@Service
public class TypeDefiner {

    /**
     * Defines the class type of a given AbstractPuzzle entity.
     * <p>
     * This method retrieves the simple name of the class of the provided AbstractPuzzle object.
     *
     * @param abstractPuzzle The AbstractPuzzle instance for which the class type is to be defined.
     * @return A {@link String} representing the simple name of the class of the provided AbstractPuzzle instance.
     *         The returned value is the simple name of the class as defined by {@link Class#getSimpleName()}.
     */
    public String defineType(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzle.getClass().getSimpleName();
    }
}