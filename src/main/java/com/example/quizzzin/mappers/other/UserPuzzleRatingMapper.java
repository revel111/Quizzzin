package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.RatePuzzleDTO;
import com.example.quizzzin.models.entities.UserPuzzleRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The {@code UserPuzzleRatingMapper} interface defines mappings between the {@link UserPuzzleRating} entity
 * and the {@link RatePuzzleDTO}.
 * <p>
 * It uses MapStruct to automate the conversion process, allowing for the easy transformation of data between
 * DTOs and entity objects related to user puzzle ratings.
 * </p>
 */
@Mapper
public interface UserPuzzleRatingMapper {

    /**
     * Singleton instance of the {@code UserPuzzleRatingMapper}.
     * This instance is used to access the mapping methods defined in this interface.
     */
    UserPuzzleRatingMapper INSTANCE = Mappers.getMapper(UserPuzzleRatingMapper.class);

    /**
     * Converts a {@link RatePuzzleDTO} to a {@link UserPuzzleRating} entity.
     * <p>
     * This method maps the `RatePuzzleDTO` to an entity that represents a rating given by a user to a puzzle.
     * It includes mappings for the puzzle and user IDs, and the rating itself.
     * </p>
     *
     * @param ratePuzzleDTO The {@link RatePuzzleDTO} to be converted.
     * @return A {@link UserPuzzleRating} entity with fields populated from the provided {@code RatePuzzleDTO}.
     */
    @Mapping(source = "puzzle", target = "puzzle")
    @Mapping(source = "puzzle.id", target = "id.puzzleId")
    @Mapping(source = "user.id", target = "id.userId")
    UserPuzzleRating toUserPuzzleRating(RatePuzzleDTO ratePuzzleDTO);
}