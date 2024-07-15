package com.example.quizzzin.mappers.add;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.puzzles.add.AddRiddleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveRiddleDTO;
import com.example.quizzzin.models.entities.Difficulty;
import com.example.quizzzin.models.entities.Riddle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RiddleMapper {
    RiddleMapper INSTANCE = Mappers.getMapper(RiddleMapper.class);

//    @Mapping(target = "difficultyType", source = "difficulty.name")
//    RiddleDTO toRiddleDTO(Riddle riddle);

    Riddle toRiddle(AddRiddleDTO riddleDTO);

    @Mapping(source = "difficulty.name", target = "difficultyType")
    SolveRiddleDTO toSolveRiddleDTO(Riddle riddle);

    default DifficultyType map(Difficulty difficulty) {
        return difficulty.getName();
    }
}
