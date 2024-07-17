package com.example.quizzzin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    public Page<AbstractPuzzle> getPuzzlesPage(int pageNum/*, String sortField, String sortDirection*/) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(sortField).ascending()
//                : Sort.by(sortField).descending();

//        if (sortField.equalsIgnoreCase("rating"))
//            return abstractPuzzleRepository.findAll(AbstractPuzzleSpecifications.byAverageRating(),
//                    PageRequest.of(pageNum - 1, 3, sort));

        return abstractPuzzleRepository.findAll(PageRequest.of(pageNum - 1, 3/*, sort*/));
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