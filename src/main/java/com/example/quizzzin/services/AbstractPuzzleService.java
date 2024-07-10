package com.example.quizzzin.services;

import com.example.quizzzin.mappers.get.AbstractPuzzleMapper;
import com.example.quizzzin.models.dto.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.repositories.AbstractPuzzleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AbstractPuzzleService {
    private final AbstractPuzzleRepository abstractPuzzleRepository;
    private final AbstractPuzzleMapper abstractPuzzleMapper = AbstractPuzzleMapper.INSTANCE;

    public Page<AbstractPuzzle> getPuzzlesPage(int pageNum) {
        return abstractPuzzleRepository.findAll(PageRequest.of(pageNum - 1, 3));
    }

    public Optional<AbstractPuzzle> findAbstractPuzzleById(long id) {
        return abstractPuzzleRepository.findById(id);
    }

    public ViewAbstractPuzzleDTO toViewAbstractPuzzleDTO(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzleMapper.toViewDTO(abstractPuzzle);
    }

    public Page<FeedViewAbstractPuzzleDTO> toFeedViewPuzzlePage(Page<AbstractPuzzle> puzzlePage) {
        List<FeedViewAbstractPuzzleDTO> abstractPuzzleDTOList = puzzlePage.stream()
                .map(x -> new FeedViewAbstractPuzzleDTO(x.getId(), x.getDifficulty().getName(), x.getTitle()))
                .toList();
        return new PageImpl<>(abstractPuzzleDTOList, PageRequest.of(puzzlePage.getNumber(), puzzlePage.getSize()), puzzlePage.getTotalElements());
    }
}