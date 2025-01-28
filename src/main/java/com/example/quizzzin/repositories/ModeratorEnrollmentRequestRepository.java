package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.ModeratorEnrollmentRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorEnrollmentRequestRepository extends CrudRepository<ModeratorEnrollmentRequest, Long> {
}