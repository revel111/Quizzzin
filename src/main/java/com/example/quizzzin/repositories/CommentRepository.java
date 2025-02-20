package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The CommentRepository interface provides CRUD operations for Comment entities.
 * It extends the {@link CrudRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link CrudRepository}: Spring Data interface for generic CRUD operations.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByPuzzleId(Long puzzleId);

}