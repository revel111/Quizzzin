package com.example.quizzzin.controllers.api;

import com.example.quizzzin.models.dto.other.AddCommentDTO;
import com.example.quizzzin.models.entities.Comment;
import com.example.quizzzin.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * The {@code CommentAPIController} class is a REST controller responsible for handling HTTP requests related
 * to comments within the application.
 * It provides endpoints for creating, updating, and deleting comments.
 * <p>
 * This controller uses the {@link CommentService} to perform operations on comments and map entities to
 * Data Transfer Objects (DTOs) for client responses.
 * </p>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentAPIController {
    private final CommentService commentService;

    /**
     * Creates a new comment based on the provided {@link AddCommentDTO}.
     * <p>
     * The comment will be saved using the {@link CommentService}. If the user or puzzle specified in the DTO is
     * incorrect, a bad request response is returned.
     * </p>
     *
     * @param commentDTO the data transfer object containing the details of the comment to be added
     * @return a {@link ResponseEntity} with the status and body of the response. If the comment is successfully
     * created, the response body contains the details of the newly created comment. Otherwise, a bad
     * request response is returned if the user or puzzle is incorrect.
     */
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody AddCommentDTO commentDTO) {
        Optional<Comment> comment = commentService.saveComment(commentDTO);

        if (comment.isEmpty())
            return ResponseEntity.badRequest().body("User or puzzle was specified incorrectly.");

        return ResponseEntity.ok().body(commentService.toViewCommentDTO(comment.get()));
    }

    /**
     * Deletes an existing comment identified by the provided ID.
     * <p>
     * The comment is removed using the {@link CommentService}. If the comment is not found, a not found response
     * is returned.
     * </p>
     *
     * @param id the ID of the comment to be deleted
     * @return a {@link ResponseEntity} indicating the result of the deletion. If the comment is successfully
     * deleted, an OK response is returned. If the comment is not found, a not found response is returned.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable long id) {
        Optional<Comment> comment = commentService.findCommentById(id);

        if (comment.isEmpty())
            return ResponseEntity.notFound().build();

        commentService.deleteCommentById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates an existing comment identified by the provided ID with new content.
     * <p>
     * The comment is updated using the {@link CommentService}. The content to be updated is provided in a map
     * with a key of "content". If the comment is not found or the update fails, a bad request response is returned.
     * </p>
     *
     * @param id      the ID of the comment to be updated
     * @param payload a map containing the new content for the comment, with the key "content"
     * @return a {@link ResponseEntity} with the status and body of the response. If the comment is successfully
     * updated, the response body contains the updated comment details. If the comment is not found or
     * the update fails, a bad request response is returned.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable long id, @RequestBody Map<String, String> payload) {
        Optional<Comment> comment = commentService.updateComment(id, payload.get("content"));

        if (comment.isEmpty())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(commentService.toViewCommentDTO(comment.get()));
    }
}