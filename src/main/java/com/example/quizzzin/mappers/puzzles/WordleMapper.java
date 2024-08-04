package com.example.quizzzin.mappers.puzzles;

import com.example.quizzzin.models.dto.puzzles.add.AddWordleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveWordleDTO;
import com.example.quizzzin.models.entities.Wordle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The {@code WordleMapper} interface defines the mapping between the {@link Wordle} entity
 * and the data transfer objects ({@link AddWordleDTO} and {@link SolveWordleDTO}).
 * <p>
 * It uses MapStruct to automate the mapping process, converting between entity objects
 * and DTOs used for communication between different layers of the application.
 * </p>
 */
@Mapper
public interface WordleMapper {

    /**
     * Singleton instance of the {@code WordleMapper}.
     * This instance is used to access the mapping methods defined in this interface.
     */
    WordleMapper INSTANCE = Mappers.getMapper(WordleMapper.class);

    /**
     * Converts an {@link AddWordleDTO} object to a {@link Wordle} entity.
     *
     * @param wordleDTO The data transfer object containing the information to be mapped to a Wordle entity.
     * @return A {@link Wordle} entity with fields populated from the provided {@code AddWordleDTO}.
     */
    Wordle toWordle(AddWordleDTO wordleDTO);

    /**
     * Converts a {@link Wordle} entity to a {@link SolveWordleDTO} data transfer object.
     * <p>
     * The {@code difficulty.name} field in the {@link Wordle} entity is mapped to the {@code difficultyType}
     * field in the {@link SolveWordleDTO}.
     * </p>
     *
     * @param wordle The {@link Wordle} entity to be converted.
     * @return A {@link SolveWordleDTO} with fields populated from the provided {@code Wordle} entity.
     */
    @Mapping(source = "difficulty.name", target = "difficultyType")
    SolveWordleDTO toSolveWordleDTO(Wordle wordle);
}