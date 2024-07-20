package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPuzzleScoreMapper {
    UserPuzzleScoreMapper INSTANCE = Mappers.getMapper(UserPuzzleScoreMapper.class);

    @Mapping(source = "user.nickname", target = "nickname")
    @Mapping(source = "user.id", target = "userId")
    LeaderboardDTO toLeaderboardDTO(UserPuzzleScore userPuzzleScore);
}