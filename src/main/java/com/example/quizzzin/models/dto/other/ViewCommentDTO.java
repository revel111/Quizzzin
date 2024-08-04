package com.example.quizzzin.models.dto.other;

/**
 * The {@code ViewCommentDTO} record represents the data transfer object (DTO) used for
 * viewing comments. It encapsulates the essential details of a comment, including its
 * identifier, the user who made the comment, and the content of the comment.
 * <p>
 * This record provides a simple and immutable structure for transmitting comment data
 * from one layer of the application to another, typically for presentation in a user interface.
 * </p>
 *
 * @param id           The unique identifier of the comment.
 * @param userId       The unique identifier of the user who made the comment.
 * @param username     The username of the user who made the comment.
 * @param dateOfAdding The date and time when the comment was added.
 * @param text         The content of the comment.
 */
public record ViewCommentDTO(Long id, Long userId, String username, String dateOfAdding, String text) {
}