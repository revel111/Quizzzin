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

/**
 * The AbstractPuzzleService class provides operations for managing puzzles in the application.
 * This includes retrieving paginated puzzles, converting puzzle entities to DTOs,
 * and finding puzzles by their ID.
 * <p>
 * Dependencies:
 * - {@link AbstractPuzzleRepository}: Repository for puzzle persistence operations.
 * - {@link AbstractPuzzleMapper}: Mapper for converting between AbstractPuzzle entities and DTOs.
 */
@AllArgsConstructor
@Service
public class AbstractPuzzleService {
    private final AbstractPuzzleRepository abstractPuzzleRepository;
    private final AbstractPuzzleMapper abstractPuzzleMapper = AbstractPuzzleMapper.INSTANCE;

    /**
     * Retrieves a paginated list of puzzles sorted by the specified criteria.
     *
     * @param pageNum The page number to retrieve.
     * @param sortBy  The field to sort by (difficulty, rating, date).
     * @param dir     The direction of sorting (asc or desc).
     * @return A paginated list of {@link AbstractPuzzle} entities.
     */
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

    /**
     * Finds a puzzle by its ID.
     *
     * @param id The ID of the puzzle to find.
     * @return An Optional containing the found {@link AbstractPuzzle} entity, or empty if not found.
     */
    public Optional<AbstractPuzzle> findAbstractPuzzleById(long id) {
        return abstractPuzzleRepository.findById(id);
    }

    /**
     * Converts an AbstractPuzzle entity to a ViewAbstractPuzzleDTO using the AbstractPuzzleMapper.
     *
     * @param abstractPuzzle The AbstractPuzzle entity to convert.
     * @return The converted {@link ViewAbstractPuzzleDTO}.
     */
    public ViewAbstractPuzzleDTO toViewAbstractPuzzleDTO(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzleMapper.toViewDTO(abstractPuzzle);
    }

    /**
     * Converts an AbstractPuzzle entity to a FeedViewAbstractPuzzleDTO using the AbstractPuzzleMapper.
     *
     * @param abstractPuzzle The AbstractPuzzle entity to convert.
     * @return The converted {@link FeedViewAbstractPuzzleDTO}.
     */
    public FeedViewAbstractPuzzleDTO toFeedViewAbstractPuzzleDTO(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzleMapper.toFeedViewDTO(abstractPuzzle);
    }

    /**
     * Converts a paginated list of AbstractPuzzle entities to a paginated list of FeedViewAbstractPuzzleDTOs.
     *
     * @param puzzlePage The page of AbstractPuzzle entities to convert.
     * @return A paginated list of {@link FeedViewAbstractPuzzleDTO}.
     */
    public Page<FeedViewAbstractPuzzleDTO> toFeedViewPuzzlePage(Page<AbstractPuzzle> puzzlePage) {
        List<FeedViewAbstractPuzzleDTO> abstractPuzzleDTOList = puzzlePage.stream()
                .map(this::toFeedViewAbstractPuzzleDTO)
                .toList();
        return new PageImpl<>(abstractPuzzleDTOList, PageRequest.of(puzzlePage.getNumber(), puzzlePage.getSize()), puzzlePage.getTotalElements());
    }
}