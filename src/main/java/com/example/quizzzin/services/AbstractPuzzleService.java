package com.example.quizzzin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.quizzzin.mappers.puzzles.AbstractPuzzleMapper;
import com.example.quizzzin.models.dto.puzzles.get.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.puzzles.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.repositories.AbstractPuzzleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AbstractPuzzleService {
    private final AbstractPuzzleRepository abstractPuzzleRepository;
    private final AbstractPuzzleMapper abstractPuzzleMapper = AbstractPuzzleMapper.INSTANCE;

    public Page<AbstractPuzzle> getPuzzlesPage(int pageNum, String sortBy, String dir) {
        Page<AbstractPuzzle> page;
        Sort sort;
        Pageable pageable = PageRequest.of(pageNum - 1, 3);
        boolean ascending = dir.equalsIgnoreCase(Sort.Direction.ASC.name());

        switch (sortBy) {
            case "difficulty" -> {
                page = ascending ? abstractPuzzleRepository.findAllByOrderByDifficultyAsc(pageable)
                        : abstractPuzzleRepository.findAllByOrderByDifficultyDesc(pageable);
            }
            case "rating" -> {
                page = ascending ? abstractPuzzleRepository.findAllWithAverageRatingAsc(pageable)
                        : abstractPuzzleRepository.findAllWithAverageRatingDesc(pageable);
            }
            case "date" -> {
                sort = ascending
                        ? Sort.by("dateOfAdding").ascending()
                        : Sort.by("dateOfAdding").descending();
                pageable = PageRequest.of(pageNum - 1, 3, sort);
                page = abstractPuzzleRepository.findAll(pageable);
            }
            default -> {
                return null;
            }
        }

        return page;
    }

    public Optional<AbstractPuzzle> findAbstractPuzzleById(long id) {
        return abstractPuzzleRepository.findById(id);
    }

    public ViewAbstractPuzzleDTO toViewAbstractPuzzleDTO(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzleMapper.toViewDTO(abstractPuzzle);
    }

    public FeedViewAbstractPuzzleDTO toFeedViewAbstractPuzzleDTO(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzleMapper.toFeedViewDTO(abstractPuzzle);
    }

    public Page<FeedViewAbstractPuzzleDTO> toFeedViewPuzzlePage(Page<AbstractPuzzle> puzzlePage) {
        List<FeedViewAbstractPuzzleDTO> abstractPuzzleDTOList = puzzlePage.stream()
                .map(this::toFeedViewAbstractPuzzleDTO)
                .toList();
        return new PageImpl<>(abstractPuzzleDTOList, PageRequest.of(puzzlePage.getNumber(), puzzlePage.getSize()), puzzlePage.getTotalElements());
    }
}