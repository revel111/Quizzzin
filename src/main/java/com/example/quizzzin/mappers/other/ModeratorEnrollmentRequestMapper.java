package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.ModeratorEnrollmentDTO;
import com.example.quizzzin.models.entities.ModeratorEnrollmentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModeratorEnrollmentRequestMapper {
    ModeratorEnrollmentRequestMapper INSTANCE = Mappers.getMapper(ModeratorEnrollmentRequestMapper.class);

    ModeratorEnrollmentRequest toModeratorEnrollmentRequest(ModeratorEnrollmentDTO moderatorEnrollmentDTO);
}