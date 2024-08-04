package com.example.quizzzin.services;

import com.example.quizzzin.mappers.other.CommentMapper;
import com.example.quizzzin.models.dto.other.AddCommentDTO;
import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.Comment;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The CommentService class provides operations for managing comments in the application.
 * This includes creating, updating, deleting, and retrieving comments.
 * <p>
 * Dependencies:
 * - {@link CommentRepository}: Repository for comment persistence operations.
 * - {@link CommentMapper}: Mapper for converting between Comment entities and DTOs.
 * - {@link UserService}: Service for managing user-related operations.
 * - {@link AbstractPuzzleService}: Service for managing puzzle-related operations.
 */
@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    private final UserService userService;
    private final AbstractPuzzleService abstractPuzzleService;

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment to delete.
     */
    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

    /**
     * Finds a comment by its ID.
     *
     * @param id The ID of the comment to find.
     * @return An Optional containing the found {@link Comment} entity, or empty if not found.
     */
    public Optional<Comment> findCommentById(long id) {
        return commentRepository.findById(id);
    }

    /**
     * Converts a Comment entity to a ViewCommentDTO using the CommentMapper.
     *
     * @param comment The Comment entity to convert.
     * @return The converted {@link ViewCommentDTO}.
     */
    public ViewCommentDTO toViewCommentDTO(Comment comment) {
        return commentMapper.toViewCommentDTO(comment);
    }

    /**
     * Saves a new comment.
     * Converts the provided AddCommentDTO to a Comment entity,
     * sets the user and puzzle from their respective services,
     * and saves the comment to the repository.
     *
     * @param addCommentDTO The data transfer object containing comment details.
     * @return An Optional containing the saved {@link Comment} entity, or empty if the user or puzzle is not found.
     */
    public Optional<Comment> saveComment(AddCommentDTO addCommentDTO) {
        Optional<User> user = userService.findUserById(addCommentDTO.idUser());
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(addCommentDTO.idPuzzle());

        if (user.isEmpty() || abstractPuzzle.isEmpty())
            return Optional.empty();

        Comment comment = new Comment();
        comment.setUser(user.get());
        comment.setPuzzle(abstractPuzzle.get());
        comment.setText(addCommentDTO.text());

        return Optional.of(commentRepository.save(comment));
    }

    /**
     * Updates the content of an existing comment.
     *
     * @param id      The ID of the comment to update.
     * @param content The new content of the comment.
     * @return An Optional containing the updated {@link Comment} entity, or empty if the comment is not found.
     */
    public Optional<Comment> updateComment(long id, String content) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isEmpty())
            return Optional.empty();

        comment.get().setText(content);

        return Optional.of(commentRepository.save(comment.get()));
    }
}