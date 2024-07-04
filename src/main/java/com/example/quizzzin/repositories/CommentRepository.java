package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.Comment;
import com.example.quizzzin.models.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
