package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code CommentMapper} interface defines mappings between the {@link Comment} entity
 * and the {@link ViewCommentDTO} data transfer object. It uses MapStruct to handle the conversion
 * between these two types.
 * <p>
 * This interface provides a way to automatically map data between the entity representing comments
 * and the DTO used for representing comments in views.
 * </p>
 */
@Mapper
public interface CommentMapper {

    /**
     * Singleton instance of the {@code CommentMapper}.
     * This instance is used to access the mapping methods defined in this interface.
     */
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    /**
     * Converts a {@link Comment} entity to a {@link ViewCommentDTO}.
     * <p>
     * This method maps the fields of {@code Comment} to the {@link ViewCommentDTO}. The user's nickname
     * and ID are mapped to the corresponding fields in the DTO. The date of adding is formatted using the
     * specified date format ("dd-MM-yyyy").
     * </p>
     *
     * @param comment The {@link Comment} entity to be converted.
     * @return A {@link ViewCommentDTO} with fields populated from the provided {@code Comment} entity.
     */
    @Mapping(source = "user.nickname", target = "username")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "dateOfAdding", expression = "java(mapDate(comment.getDateOfAdding()))")
    ViewCommentDTO toViewCommentDTO(Comment comment);

    /**
     * Converts a {@link LocalDateTime} to a formatted date string.
     * <p>
     * This method formats the {@code LocalDateTime} using the pattern "dd-MM-yyyy".
     * </p>
     *
     * @param dateOfAdding The {@link LocalDateTime} to be formatted.
     * @return A formatted date string in the "dd-MM-yyyy" format.
     */
    default String mapDate(LocalDateTime dateOfAdding) {
        return dateOfAdding.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}