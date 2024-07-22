package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "user.nickname", target = "username")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "dateOfAdding", expression = "java(mapDate(comment.getDateOfAdding()))")
    ViewCommentDTO toViewCommentDTO(Comment comment);

    default String mapDate(LocalDateTime dateOfAdding) {
        return dateOfAdding.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}