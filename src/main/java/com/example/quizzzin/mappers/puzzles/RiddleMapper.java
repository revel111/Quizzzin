package com.example.quizzzin.mappers.puzzles;

import com.example.quizzzin.models.dto.puzzles.add.AddRiddleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveRiddleDTO;
import com.example.quizzzin.models.entities.Riddle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * The {@code RiddleMapper} interface defines the mapping between the {@link Riddle} entity
 * and the data transfer objects ({@link AddRiddleDTO} and {@link SolveRiddleDTO}).
 * <p>
 * It uses MapStruct to automate the mapping process, converting between entity objects
 * and DTOs used for communication between different layers of the application.
 * </p>
 */
@Mapper
public interface RiddleMapper {

    /**
     * Singleton instance of the {@code RiddleMapper}.
     * This instance is used to access the mapping methods defined in this interface.
     */
    RiddleMapper INSTANCE = Mappers.getMapper(RiddleMapper.class);

    /**
     * Converts an {@link AddRiddleDTO} object to a {@link Riddle} entity.
     *
     * @param riddleDTO The data transfer object containing the information to be mapped to a Riddle entity.
     * @return A {@link Riddle} entity with fields populated from the provided {@code AddRiddleDTO}.
     */
    Riddle toRiddle(AddRiddleDTO riddleDTO);

    /**
     * Converts a {@link Riddle} entity to a {@link SolveRiddleDTO} data transfer object.
     * <p>
     * The {@code difficulty.name} field in the {@link Riddle} entity is mapped to the {@code difficultyType}
     * field in the {@link SolveRiddleDTO}.
     * </p>
     *
     * @param riddle The {@link Riddle} entity to be converted.
     * @return A {@link SolveRiddleDTO} with fields populated from the provided {@code Riddle} entity.
     */
    @Mapping(source = "difficulty.name", target = "difficultyType")
    SolveRiddleDTO toSolveRiddleDTO(Riddle riddle);
}