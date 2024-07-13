package com.example.quizzzin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.quizzzin.models.entities.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
