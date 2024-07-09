package com.example.quizzzin.mappers;

import com.example.quizzzin.models.dto.RiddleDTO;
import com.example.quizzzin.models.dto.WordleDTO;
import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.models.entities.Wordle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WordleMapper {
    WordleMapper INSTANCE = Mappers.getMapper(WordleMapper.class);

    Wordle toRiddle(WordleDTO WordleDTO);
}
