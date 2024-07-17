package com.example.quizzzin.mappers.puzzles;

import com.example.quizzzin.models.dto.puzzles.add.AddWordleDTO;
import com.example.quizzzin.models.entities.Wordle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WordleMapper {
    WordleMapper INSTANCE = Mappers.getMapper(WordleMapper.class);

    Wordle toWordle(AddWordleDTO WordleDTO);
}
