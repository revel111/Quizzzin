package com.example.quizzzin.models.dto.other;

/**
 * The {@code AddCommentDTO} record represents a data transfer object (DTO) used for adding comments.
 * It encapsulates the information needed to create a new comment associated with a user and a puzzle.
 * <p>
 * This record is used to transfer data required to add a comment in the application, including the
 * text of the comment and identifiers for the user and puzzle.
 * </p>
 *
 * @param text     The content of the comment. This field contains the text that the user wants to add as a comment.
 * @param idUser   The unique identifier of the user who is adding the comment. This field associates the comment with the user.
 * @param idPuzzle The unique identifier of the puzzle to which the comment is related. This field links the comment to a specific puzzle.
 */
public record AddCommentDTO(String text, long idUser, long idPuzzle) {
}