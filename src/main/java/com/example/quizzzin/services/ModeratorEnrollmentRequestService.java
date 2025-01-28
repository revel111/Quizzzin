package com.example.quizzzin.services;

import com.example.quizzzin.mappers.other.ModeratorEnrollmentRequestMapper;
import com.example.quizzzin.models.dto.other.ModeratorEnrollmentDTO;
import com.example.quizzzin.models.entities.ModeratorEnrollmentRequest;
import com.example.quizzzin.repositories.ModeratorEnrollmentRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModeratorEnrollmentRequestService {
    private final ModeratorEnrollmentRequestRepository moderatorEnrollmentRequestRepository;
    private final ModeratorEnrollmentRequestMapper moderatorEnrollmentRequestMapper = ModeratorEnrollmentRequestMapper.INSTANCE;
    private final UserService userService;

    public ModeratorEnrollmentRequest saveRequest(ModeratorEnrollmentDTO moderatorEnrollmentDTO, String email) {
        ModeratorEnrollmentRequest moderatorEnrollmentRequest = toModeratorEnrollmentRequest(moderatorEnrollmentDTO);
        moderatorEnrollmentRequest.setUser(userService.findUserByEmail(email).get());

        return moderatorEnrollmentRequestRepository.save(moderatorEnrollmentRequest);
    }

    private ModeratorEnrollmentRequest toModeratorEnrollmentRequest(ModeratorEnrollmentDTO moderatorEnrollmentDTO) {
        return moderatorEnrollmentRequestMapper.toModeratorEnrollmentRequest(moderatorEnrollmentDTO);
    }

    public Optional<ModeratorEnrollmentRequest> findModeratorEnrollmentRequestById(Long id) {
        return moderatorEnrollmentRequestRepository.findById(id);
    }
}