package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.entities.Comment;
import com.example.quizzzin.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "user.nickname", target = "username")
    @Mapping(source = "user.id", target = "userId")
    ViewCommentDTO toViewCommentDTO(Comment comment);
}
