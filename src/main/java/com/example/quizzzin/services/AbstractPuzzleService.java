package com.example.quizzzin.services;

import com.example.quizzzin.mappers.get.AbstractPuzzleMapper;
import com.example.quizzzin.models.dto.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.repositories.AbstractPuzzleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AbstractPuzzleService {
    private final AbstractPuzzleRepository abstractPuzzleRepository;
    private final AbstractPuzzleMapper abstractPuzzleMapper = AbstractPuzzleMapper.INSTANCE;

//    public Page<AbstractPuzzle> getPuzzlesPage() {
//        return abstractPuzzleRepository.findAll(PageRequest.of(abstractPuzzleRepository.count() / 15, 15));
//    }

    public Optional<AbstractPuzzle> findAbstractPuzzleById(long id) {
        return abstractPuzzleRepository.findById(id);
    }

    public ViewAbstractPuzzleDTO fabricateViewAbstractPuzzleDTO(AbstractPuzzle abstractPuzzle) {
//        ViewAbstractPuzzleDTO viewAbstractPuzzleDTO = abstractPuzzleMapper.toDTO(abstractPuzzle);
        return abstractPuzzleMapper.toDTO(abstractPuzzle);
    }
}