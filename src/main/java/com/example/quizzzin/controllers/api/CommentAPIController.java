package com.example.quizzzin.controllers.api;

import com.example.quizzzin.models.dto.other.AddCommentDTO;
import com.example.quizzzin.models.entities.Comment;
import com.example.quizzzin.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentAPIController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody AddCommentDTO commentDTO) {
        Optional<Comment> comment = commentService.saveComment(commentDTO);

        if (comment.isEmpty())
            return ResponseEntity.badRequest().body("User or puzzle was specified incorrectly.");

        return ResponseEntity.ok().body(commentService.toViewCommentDTO(comment.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable long id) {
        Optional<Comment> comment = commentService.findCommentById(id);

        if (comment.isEmpty())
            return ResponseEntity.notFound().build();

        commentService.deleteCommentById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable long id, @RequestBody Map<String, String> payload) {
        Optional<Comment> comment = commentService.updateComment(id, payload.get("content"));

        if (comment.isEmpty())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(commentService.toViewCommentDTO(comment.get()));
    }
}