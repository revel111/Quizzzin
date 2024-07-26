package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.RatePuzzleDTO;
import com.example.quizzzin.models.entities.UserPuzzleRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPuzzleRatingMapper {
    UserPuzzleRatingMapper INSTANCE = Mappers.getMapper(UserPuzzleRatingMapper.class);

    @Mapping(source = "puzzle", target = "puzzle")
    @Mapping(source = "puzzle.id", target = "id.puzzleId")
    @Mapping(source = "user.id", target = "id.userId")
    UserPuzzleRating toUserPuzzleRating(RatePuzzleDTO ratePuzzleDTO);
}