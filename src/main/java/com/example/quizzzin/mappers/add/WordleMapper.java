package com.example.quizzzin.mappers.add;

import com.example.quizzzin.models.dto.add.AddWordleDTO;
import com.example.quizzzin.models.entities.Wordle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WordleMapper {
    WordleMapper INSTANCE = Mappers.getMapper(WordleMapper.class);

    Wordle toWordle(AddWordleDTO WordleDTO);
}
