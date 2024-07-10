package com.example.quizzzin.mappers.add;

import com.example.quizzzin.models.dto.add.AddRiddleDTO;
import com.example.quizzzin.models.entities.Riddle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RiddleMapper {
    RiddleMapper INSTANCE = Mappers.getMapper(RiddleMapper.class);

//    @Mapping(target = "difficultyType", source = "difficulty.name")
//    RiddleDTO toRiddleDTO(Riddle riddle);

    Riddle toRiddle(AddRiddleDTO riddleDTO);
}
