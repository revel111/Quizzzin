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

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    private final UserService userService;
    private final AbstractPuzzleService abstractPuzzleService;

    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

    public Optional<Comment> findCommentById(long id) {
        return commentRepository.findById(id);
    }

    public ViewCommentDTO toViewCommentDTO(Comment comment) {
        return commentMapper.toViewCommentDTO(comment);
    }

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

    public Optional<Comment> updateComment(long id, String content) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isEmpty())
            return Optional.empty();

        comment.get().setText(content);

        return Optional.of(commentRepository.save(comment.get()));
    }
}